package com.yang.core.serializable;

/**
 * 1、当父类继承Serializable接口时，所有子类都可以被序列化
 * 
 * 2、子类实现了Serializable接口，父类没有，父类中的属性不能序列化（不报错，数据丢失），但是在子类中属性仍能正确序列化
 * 
 * 3、如果序列化的属性是对象，则这个对象也必须实现Serializable接口，否则会报错
 * 
 * 4、反序列化时，如果对象的属性有修改或删减，则修改的部分属性会丢失，但不会报错
 * 
 * 5、反序列化时，如果serialVersionUID被修改，则反序列化时会报错
 * 
 * @date 2018年6月4日 上午10:29:27
 * @author tonasun
 */
public class SerializableTest {

}
