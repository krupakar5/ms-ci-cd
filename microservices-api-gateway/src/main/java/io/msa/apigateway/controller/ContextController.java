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

import io.msa.apigateway.models.Context;
import io.msa.apigateway.services.ContextIntegrationService;
import io.swagger.annotations.Api;
import rx.Observable;
import rx.Observer;

@CrossOrigin
@RestController
@RequestMapping("/context")
@Api(value = "CONTEXT", description = "API Gateway ContextController")
public class ContextController {

	@Autowired
	ContextIntegrationService ctxIntegrationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public DeferredResult<Context> createContext(@RequestBody Context context, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Context> lstContent = ctxIntegrationService.createContext(context, token);
		return toDeferredResultt(lstContent);
	}

	@RequestMapping(value = "/getctxbyuid/{userId}", method = RequestMethod.GET)
	public DeferredResult<Collection<Context>> findByUserId(@PathVariable int userId, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Context>> lstContext = ctxIntegrationService.findByUserId(userId, token);
		return toDeferredResult(lstContext);
	}

	@RequestMapping(value = "/getctxbyip", method = RequestMethod.POST)
	public DeferredResult<Context> findCtxByIp(@RequestBody Context ctx, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Context> lstContent = ctxIntegrationService.findCtxByIp(ctx, token);
		return toDeferredResultt(lstContent);
	}

	@RequestMapping(value = "/deletebyuid/{userId}", method = RequestMethod.DELETE)
	public DeferredResult<Collection<Context>> deleteByUserId(@PathVariable int userId, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Context>> lstContext = ctxIntegrationService.deleteByUserId(userId, token);
		return toDeferredResult(lstContext);
	}

	@RequestMapping(value = "/deletebyid/{id}", method = RequestMethod.DELETE)
	public DeferredResult<Collection<Context>> deleteById(@PathVariable String id, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Context>> lstContext = ctxIntegrationService.deleteById(id, token);
		return toDeferredResult(lstContext);
	}

	public DeferredResult<Context> toDeferredResultt(Observable<Context> lstContent) {
		DeferredResult<Context> result = new DeferredResult<>();
		lstContent.subscribe(new Observer<Context>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Context lstContext) {
				result.setResult(lstContext);
			}
		});
		return result;
	}

	public DeferredResult<Collection<Context>> toDeferredResult(Observable<Collection<Context>> lstContent) {
		DeferredResult<Collection<Context>> result = new DeferredResult<>();
		lstContent.subscribe(new Observer<Collection<Context>>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Collection<Context> lstContext) {
				result.setResult(lstContext);
			}
		});
		return result;
	}
}
