package uk.kainos.seleniumframework;

import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

public class ProjectEntity {

    public static String getProjectName = PropertyLoader.getProperty(CommonProperties.PROJECT_NAME) != null ?
            PropertyLoader.getProperty(CommonProperties.PROJECT_NAME) :
            "AUTOMATED SPECS TEST";
}
