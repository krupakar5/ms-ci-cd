package io.msa.context.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.msa.context.models.Context;
import io.msa.context.services.ContextService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/context")
public class ContextController {
	private static final Logger log = LoggerFactory.getLogger(ContextController.class);

	@Autowired
	private ContextService contextService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Context createContext(@RequestBody Context context) {
		return contextService.saveContext(context);
	}

	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	public Context findByContextId(@PathVariable Long id) {
		return contextService.findById(id);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public Iterable<Context> getAllCtxs() {
		return contextService.findAllCtxs();

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Context> deleteByCtxId(@PathVariable Long id) {
		Context ctx = new Context();
		try {
			contextService.deleteById(id);
			return new ResponseEntity<Context>(ctx, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Context>(ctx, HttpStatus.NO_CONTENT);

		}
	}

	@RequestMapping(value = "/getctxbyip", method = RequestMethod.POST)
	public ResponseEntity<Context> findCtxByIp(@RequestBody Context ctx, HttpServletRequest request) {

		try {
			if (ctx.getIp() == null)
				ctx.setIp(request.getRemoteAddr());
			if (ctx.getIp() == null)
				ctx.setIp(ctx.getIp());
			JSONObject jsonCtx = contextService.getCtx(ctx.getIp());

			Context context = (Context) contextService.toBean(jsonCtx, new Context());
			// Context context = new Context();
			// Add to Context collection
			// Context retCtx = contextService.saveContext(context);

			return new ResponseEntity<Context>(context, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Context>(new Context(), HttpStatus.NO_CONTENT);
		}

	}

}
