package com.mikhailsycheuski.test.warehouse.distribution.products.service

import com.mikhailsycheuski.test.warehouse.domain.products.*
import com.mikhailsycheuski.test.warehouse.domain.products.model.Product
import com.mikhailsycheuski.test.warehouse.domain.products.model.ProductUpdateRequest
import com.mikhailsycheuski.test.warehouse.domain.products.usecases.AddNewProductUseCase
import com.mikhailsycheuski.test.warehouse.domain.products.usecases.GetProductsAvailabilityUseCase
import com.mikhailsycheuski.test.warehouse.domain.products.usecases.SellProductUseCase
import com.mikhailsycheuski.test.warehouse.domain.products.usecases.UpdateProductUseCase
import org.springframework.stereotype.Service

@Service
class ProductsServiceImpl(
  private val productsRepository: ProductsRepository,
  private val addProductUseCase: AddNewProductUseCase,
  private val updateProductUseCase: UpdateProductUseCase,
  private val getProductsAvailabilityUseCase: GetProductsAvailabilityUseCase,
  private val sellProductUseCase: SellProductUseCase
) : ProductsService {

  override fun findProductById(productId: Long): Product? =
    productsRepository.findById(productId)

  override fun findProductByName(productName: String): Product? =
    productsRepository.findByName(productName)

  override fun findAllProducts(): List<Product> =
    productsRepository.findAllProducts()

  override fun addProduct(product: Product): Long =
    addProductUseCase.execute(product)

  override fun sellProduct(productId: Long) {
    sellProductUseCase.execute(productId)
  }

  override fun updateProduct(productUpdateRequest: ProductUpdateRequest) {
    updateProductUseCase.execute(productUpdateRequest)
  }

  override fun getAvailableProducts(): Map<Product, Int> =
    getProductsAvailabilityUseCase.execute()
}