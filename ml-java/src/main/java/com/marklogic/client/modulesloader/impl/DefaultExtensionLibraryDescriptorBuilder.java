package com.marklogic.client.modulesloader.impl;

import com.marklogic.client.admin.ExtensionLibraryDescriptor;
import com.marklogic.client.modulesloader.Asset;
import com.marklogic.client.modulesloader.ExtensionLibraryDescriptorBuilder;

public class DefaultExtensionLibraryDescriptorBuilder implements ExtensionLibraryDescriptorBuilder {

    private String[] rolesAndCapabilities;

    public DefaultExtensionLibraryDescriptorBuilder(String rolesAndCapabilities) {
        this.rolesAndCapabilities = rolesAndCapabilities.split(",");
    }

    @Override
    public ExtensionLibraryDescriptor buildDescriptor(Asset asset) {
        ExtensionLibraryDescriptor d = new ExtensionLibraryDescriptor();
        d.setPath("/ext" + asset.getPath());
        for (int i = 0; i < rolesAndCapabilities.length; i += 2) {
            d.addPermission(rolesAndCapabilities[i], rolesAndCapabilities[i + 1]);
        }
        return d;
    }

}
