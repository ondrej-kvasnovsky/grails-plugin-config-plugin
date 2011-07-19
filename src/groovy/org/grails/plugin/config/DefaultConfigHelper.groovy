/*
 * Copyright 2011 the original author or authors.
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
package org.grails.plugin.config

import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.codehaus.groovy.grails.plugins.GrailsPluginManager;

/**
 * 
 * @author Daniel Henrique Alves Lima
 *
 */
class DefaultConfigHelper extends AbstractDefaultConfigHelper {

    @Override
    protected void enhanceGrailsApplication(GrailsPluginManager pluginManager,
    GrailsApplication grailsApplication) {
        if (log.isDebugEnabled()) {
            log.debug("Enhancing ${grailsApplication} ${pluginManager}")
        }
        MetaClass mc = grailsApplication.metaClass
        if (!mc.respondsTo('getMergedConfig')) {
            mc._mergedConfig = null
            mc.getMergedConfig = {
                if (delegate._mergedConfig == null) {
                    delegate._mergedConfig = super.buildGetMergedConfig(pluginManager, grailsApplication)
                }

                return delegate._mergedConfig
            }
        }
    }
}
