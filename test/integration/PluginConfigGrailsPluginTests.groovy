import org.codehaus.groovy.grails.commons.ConfigurationHolder;
import org.codehaus.groovy.grails.commons.GrailsClassUtils;
import org.codehaus.groovy.grails.plugins.GrailsPlugin;

import grails.test.*



class PluginConfigGrailsPluginTests extends GroovyTestCase {

    def pluginManager
    def grailsApplication
    
    public void testOnChange() {
        
        GrailsPlugin plugin = pluginManager.getGrailsPluginForClassName('PluginConfigGrailsPlugin')
        def pluginInstance = plugin.instance
        //def pluginInstance = new PluginConfigGrailsPlugin()
        pluginInstance.metaClass.application = grailsApplication
        Closure onChange = GrailsClassUtils.getPropertyOrStaticPropertyOrFieldValue (pluginInstance, 'onChange')
        Map event = [ctx: grailsApplication.mainContext, application: grailsApplication]
        onChange(event)
        
        Closure onConfigChange = GrailsClassUtils.getPropertyOrStaticPropertyOrFieldValue (pluginInstance, 'onConfigChange')
        onConfigChange(event)
    }
    
}