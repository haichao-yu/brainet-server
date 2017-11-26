/*
 * MATLAB Compiler: 6.2 (R2016a)
 * Date: Fri Nov 24 15:41:26 2017
 * Arguments: "-B" "macro_default" "-W" "java:Comparator,Class1" "-T" "link:lib" "-d" 
 * "C:\\Users\\Haichao\\Desktop\\Matlab2Jar\\Comparator\\for_testing" 
 * "class{Class1:C:\\Users\\Haichao\\Desktop\\Comparator.m}" 
 */

package Comparator;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class ComparatorMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "Comparator_0ACFD7A01B9A192331E550EE69FB4666";
    
    /** Component name */
    private static final String sComponentName = "Comparator";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(ComparatorMCRFactory.class)
        );
    
    
    private ComparatorMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            ComparatorMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{9,0,1}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
