import Foundation
import Shared
import SwiftUI

@MainActor
class ProductsViewModelWrapper: ObservableObject {
    private let viewModel: ProductsViewModel
    
    // ✅ FIX 1: Use .shared for the data object
    @Published var state: ProductListState = ProductListStateLoading.shared
    
    init() {
        // ✅ FIX 2: This will work after you run `./gradlew :shared:assemble`
        self.viewModel = ProductsViewModelComponent().provideProductsViewModel()
    }
    
    func startObserving() {
        // ✅ FIX 3: Use the Collector pattern
        let collector = FlowCollector<ProductListState> { [weak self] state in
            self?.state = state
        }
        
        Task {
            do {
                try await viewModel.state.collect(collector: collector)
            } catch {
                print("Failed to collect state: \(error)")
            }
        }
    }
}

// ✅ Helper Class
class FlowCollector<T>: NSObject, Kotlinx_coroutines_coreFlowCollector {
    let callback: (T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        if let v = value as? T {
            callback(v)
        }
        completionHandler(nil)
    }
}