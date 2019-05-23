package io.msa.apigateway.controller;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import io.msa.apigateway.models.Content;
import io.msa.apigateway.services.ContentIntegrationService;
import io.swagger.annotations.Api;
import rx.Observable;
import rx.Observer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/content")
@Api(value = "CONTENT", description = "API Gateway ContentController")
public class ContentController {

	@Autowired
	ContentIntegrationService ctnIntegrationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public DeferredResult<Content> saveContent(@RequestBody Content content, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Content> lstContent = ctnIntegrationService.saveContent(content, token);
		return toDeferredResultt(lstContent);
	}

	@RequestMapping(value = "/getctnbyid/{ctnId}", method = RequestMethod.GET)
	public DeferredResult<Collection<Content>> findByContentId(@PathVariable Long ctnId, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Content>> lstContent = ctnIntegrationService.findByContentId(ctnId, token);
		return toDeferredResult(lstContent);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public DeferredResult<Collection<Content>> getAllContents(HttpServletRequest request, final String token) {
		String tokens = request.getHeader("authorization");
		Observable<Collection<Content>> lstContent = ctnIntegrationService.getAllContents(token);
		return toDeferredResult(lstContent);
	}

	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public DeferredResult<Collection<Content>> fetchByName(@PathVariable String name, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Content>> lstContent = ctnIntegrationService.fetchByName(name, token);
		return toDeferredResult(lstContent);
	}

	@RequestMapping(value = "/delete/{ctnId}", method = RequestMethod.DELETE)
	public DeferredResult<Collection<Content>> deleteByContentId(@PathVariable Long ctnId, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Content>> lstContent = ctnIntegrationService.deleteByContentId(ctnId, token);
		return toDeferredResult(lstContent);

	}

	public DeferredResult<Collection<Content>> toDeferredResult(Observable<Collection<Content>> lstContent) {
		DeferredResult<Collection<Content>> result = new DeferredResult<>();
		lstContent.subscribe(new Observer<Collection<Content>>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Collection<Content> lstContent) {
				result.setResult(lstContent);
			}
		});
		return result;
	}

	public DeferredResult<Content> toDeferredResultt(Observable<Content> lstContent) {
		DeferredResult<Content> result = new DeferredResult<>();
		lstContent.subscribe(new Observer<Content>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Content lstContent) {
				result.setResult(lstContent);
			}
		});
		return result;
	}
}
