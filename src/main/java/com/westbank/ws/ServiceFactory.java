package com.westbank.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

@SuppressWarnings("unchecked")
public class ServiceFactory<T> {

    public T create(Class clazz, String endpoint) {
        final JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
        jaxWsProxyFactory.setServiceClass(clazz);
        jaxWsProxyFactory.setAddress(endpoint);
        return (T) jaxWsProxyFactory.create();
    }

    public T create(Class clazz, String endpointBase, String endpointSuffix) {
        final String endpoint = endpointBase + clazz.getSimpleName() + endpointSuffix;
        return create(clazz, endpoint);
    }

}
