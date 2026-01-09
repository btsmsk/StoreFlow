import SwiftUI
import Shared

struct ContentView: View {
    // Use the wrapper we created
    @StateObject private var viewModelWrapper = ProductsViewModelWrapper()
    
    var body: some View {
        NavigationView {
            VStack {
                switch viewModelWrapper.state {
                case is ProductListState.Loading:
                    ProgressView()
                    
                case let errorState as ProductListState.Error:
                    Text("Error: \(errorState.message)")
                        .foregroundColor(.red)
                    
                case let successState as ProductListState.Success:
                    List(successState.products, id: \.id) { product in
                        ProductRow(product: product)
                    }
                    .listStyle(.plain)
                    
                default:
                    EmptyView()
                }
            }
            .navigationTitle("StoreFlow")
            .onAppear {
                viewModelWrapper.startObserving()
            }
        }
    }
}

struct ProductRow: View {
    let product: Product
    
    var body: some View {
        HStack(spacing: 16) {
            // Using AsyncImage (iOS 15+)
            AsyncImage(url: URL(string: product.thumbnail)) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
            } placeholder: {
                Color.gray.opacity(0.3)
            }
            .frame(width: 80, height: 80)
            .cornerRadius(8)
            .clipped()
            
            VStack(alignment: .leading, spacing: 4) {
                Text(product.title)
                    .font(.headline)
                    .lineLimit(1)
                
                Text("$\(String(format: "%.2f", product.price))")
                    .font(.subheadline)
                    .foregroundColor(.blue)
            }
        }
        .padding(.vertical, 4)
    }
}