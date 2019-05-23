package io.msa.apigateway;

import org.springframework.hateoas.ResourceSupport;

public class RootResource extends ResourceSupport {
	public static final String LINK_NAME_PRODUCT = "product";
	public static final String LINK_NAME_CONTENT = "content";
	public static final String LINK_NAME_CONTEXT = "context";
	public static final String LINK_NAME_ORDERS = "order";
	public static final String LINK_NAME_REVIEW = "review";

}
