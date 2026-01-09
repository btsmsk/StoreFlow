import Foundation
import Shared
import SwiftUI

// This class connects Kotlin's ViewModel to SwiftUI's Observation system
@MainActor
class ProductsViewModelWrapper: ObservableObject {
    // 1. Hold the Kotlin ViewModel
    private let viewModel: ProductsViewModel
    
    // 2. Publish the state to SwiftUI
    @Published var state: ProductListState = ProductListState.Loading()
    
    init() {
        // Inject via Koin (Helper we will add next) or create directly for now if Koin helper isn't ready
        // Ideally: self.viewModel = KoinHelper().getProductsViewModel()
        // For simplicity now, let's instantiate via Koin's helper in Kotlin or manual injection.
        
        // Let's use a Helper from Kotlin to get the ViewModel to ensure Koin is used.
        // *Wait, let's make a quick Helper in Kotlin first to make this cleaner.*
        self.viewModel = ProductsViewModelComponent().provideProductsViewModel()
    }
    
    func startObserving() {
        // 3. Collect the Kotlin Flow
        Task {
            for await state in viewModel.state {
                self.state = state
            }
        }
    }
}