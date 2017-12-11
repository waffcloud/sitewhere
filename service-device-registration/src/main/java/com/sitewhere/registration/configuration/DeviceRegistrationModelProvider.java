/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.registration.configuration;

import com.sitewhere.configuration.model.ConfigurationModelProvider;
import com.sitewhere.configuration.old.IDeviceServicesParser;
import com.sitewhere.rest.model.configuration.AttributeNode;
import com.sitewhere.rest.model.configuration.ElementNode;
import com.sitewhere.spi.microservice.configuration.model.AttributeType;
import com.sitewhere.spi.microservice.configuration.model.IConfigurationRoleProvider;

/**
 * Configuration model provider for device registration microservice.
 * 
 * @author Derek
 */
public class DeviceRegistrationModelProvider extends ConfigurationModelProvider {

    /*
     * @see com.sitewhere.spi.microservice.configuration.model.IConfigurationModel#
     * getDefaultXmlNamespace()
     */
    @Override
    public String getDefaultXmlNamespace() {
	return "http://sitewhere.io/schema/sitewhere/microservice/device-registration";
    }

    @Override
    public IConfigurationRoleProvider getRootRole() {
	return DeviceRegistrationRoles.DeviceRegistration;
    }

    /*
     * @see
     * com.sitewhere.configuration.model.MicroserviceConfigurationModel#addElements(
     * )
     */
    @Override
    public void addElements() {
	addElement(createDefaultRegistrationManagerElement());
    }

    /**
     * Create element configuration for default registration manager.
     * 
     * @return
     */
    protected ElementNode createDefaultRegistrationManagerElement() {
	ElementNode.Builder builder = new ElementNode.Builder("Registration Manager",
		IDeviceServicesParser.Elements.DefaultRegistrationManager.getLocalName(), "key",
		DeviceRegistrationRoleKeys.DeviceRegistrationManager);

	builder.description("Provides device registration management functionality.");
	builder.attribute((new AttributeNode.Builder("Allow registration of new devices", "allowNewDevices",
		AttributeType.Boolean)
			.description("Indicates whether new devices should be allowed to register with the system")
			.defaultValue("true").build()));
	builder.attribute(
		(new AttributeNode.Builder("Automatically assign site", "autoAssignSite", AttributeType.Boolean)
			.description("Indicates if a site should automatically be assigned if no site token is "
				+ "passed in registration request.")
			.build()));
	builder.attribute((new AttributeNode.Builder("Site token", "autoAssignSiteToken", AttributeType.String)
		.description("Site token used for registering new devices if auto-assign is enabled "
			+ "and no site token is passed.")
		.build()));
	return builder.build();
    }
}