/*
 * MATLAB Compiler: 6.2 (R2016a)
 * Date: Fri Nov 24 15:19:18 2017
 * Arguments: "-B" "macro_default" "-W" "java:FeatureExt,Class1" "-T" "link:lib" "-d" 
 * "C:\\Users\\Haichao\\Desktop\\Matlab2Jar\\FeatureExt\\for_testing" 
 * "class{Class1:C:\\Users\\Haichao\\Desktop\\FeatureExt.m,C:\\Users\\Haichao\\Desktop\\FFT.m}" 
 */

package FeatureExt;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class FeatureExtMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "FeatureExt_9A5EC196BD0A2D4DD490BDDDF0B69591";
    
    /** Component name */
    private static final String sComponentName = "FeatureExt";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(FeatureExtMCRFactory.class)
        );
    
    
    private FeatureExtMCRFactory()
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
            FeatureExtMCRFactory.class, 
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
