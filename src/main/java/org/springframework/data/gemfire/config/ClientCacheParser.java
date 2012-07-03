/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.gemfire.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Parser for &lt;client-cache;gt; definitions.
 * 
 * @author Costin Leau
 * @author David Turanski
 */
class ClientCacheParser extends CacheParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return ClientCacheFactoryBean.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		super.doParse(element, parserContext, builder);

		ParsingUtils.setPropertyValue(element, builder, "pool-name", "poolName");
	}

	@Override
	protected void postProcessDynamicRegionSupport(Element element, BeanDefinitionBuilder dynamicRegionSupport) {
		String poolName = element.getAttribute("pool-name");
		if (StringUtils.hasText(poolName)) {
			dynamicRegionSupport.addPropertyValue("poolName", poolName);
		}
	}
}