package io.msa.apigateway.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.msa.apigateway.models.Product;
import io.msa.apigateway.services.ProductIntegrationService;
import io.swagger.annotations.Api;
import rx.Observable;
import rx.Observer;

@CrossOrigin
@RestController
@RequestMapping("/product")
@Api(value = "PRODUCT", description = "API Gateway ProductController")
public class ProductController {

	@Autowired
	ProductIntegrationService productIntegrationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public DeferredResult<Product> saveProduct(@RequestBody Product product, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Product> lstProduct = productIntegrationService.saveProduct(product, token);
		return toDeferredResultt(lstProduct);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DeferredResult<Collection<Product>> fetchByProductId(@PathVariable Long id, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Product>> lstProduct = productIntegrationService.findByProductId(id, token);
		return toDeferredResult(lstProduct);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public DeferredResult<Collection<Product>> getAllProducts(HttpServletRequest request, final String token) {
		String tokens = request.getHeader("authorization");
		Observable<Collection<Product>> lstContent = productIntegrationService.getAllProducts(tokens);
		return toDeferredResult(lstContent);
	}

	@RequestMapping(value = "/findbyProName/{productName}", method = RequestMethod.GET)
	public DeferredResult<Collection<Product>> fetchByName(@PathVariable String productName,
			HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Product>> lstProduct = productIntegrationService.fetchByProductName(productName, token);
		return toDeferredResult(lstProduct);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public DeferredResult<Collection<Product>> deleteByProductId(@PathVariable Long id, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Product>> lstProduct = productIntegrationService.deleteByProductId(id, token);
		return toDeferredResult(lstProduct);

	}

	public DeferredResult<Collection<Product>> toDeferredResult(Observable<Collection<Product>> lstProduct) {
		DeferredResult<Collection<Product>> result = new DeferredResult<>();
		lstProduct.subscribe(new Observer<Collection<Product>>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Collection<Product> lstProduct) {
				result.setResult(lstProduct);
			}
		});
		return result;
	}

	public DeferredResult<Product> toDeferredResultt(Observable<Product> lstProduct) {
		DeferredResult<Product> result = new DeferredResult<>();
		lstProduct.subscribe(new Observer<Product>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Product lstProduct) {
				result.setResult(lstProduct);
			}
		});
		return result;
	}

}
