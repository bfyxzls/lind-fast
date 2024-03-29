package com.lind.plugin.log.trace.instrument.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.lind.plugin.log.trace.Constants;
import com.lind.plugin.log.trace.TraceContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TracePreZuulFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(TracePreZuulFilter.class);

	private final TraceContentFactory traceContentFactory;

	public TracePreZuulFilter(TraceContentFactory traceContentFactory) {
		this.traceContentFactory = traceContentFactory;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// 1. 传递 traceId
		RequestContext context = RequestContext.getCurrentContext();
		Map<String, String> copyOfContextMap = traceContentFactory.assemblyTraceContent();
		for (Map.Entry<String, String> copyOfContext : copyOfContextMap.entrySet()) {
			context.addZuulRequestHeader(copyOfContext.getKey(), copyOfContext.getValue());
		}
		// 2. 获取 traceId
		String traceId = MDC.get(Constants.LEGACY_TRACE_ID_NAME);
		// 3. 处理响应的 header traceId
		if (traceId != null) {
			context.addZuulRequestHeader(Constants.LEGACY_TRACE_ID_NAME, traceId);
		}
		log.debug("zuul traceid {}", traceId);
		return null;
	}

}
