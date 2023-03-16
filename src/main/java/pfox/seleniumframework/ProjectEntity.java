package pfox.seleniumframework;

import pfox.seleniumframework.properties.CommonProperties;
import pfox.seleniumframework.properties.PropertyLoader;

public class ProjectEntity {

    public static String getProjectName = PropertyLoader.getProperty(CommonProperties.PROJECT_NAME) != null ?
            PropertyLoader.getProperty(CommonProperties.PROJECT_NAME) :
            "AUTOMATED SPECS TEST";
}
