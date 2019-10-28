import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.spring.beans.core.BeanDefinition;
import org.spring.beans.core.di.PropertyValue;
import org.spring.beans.core.di.RuntimeReference;
import org.spring.beans.core.di.TypeStringValue;
import org.spring.beans.core.io.ClassPathResource;
import org.spring.beans.support.DefaultBeanFactory;
import org.spring.beans.xml.XMLBeanDefinitionParser;
import org.spring.service.DinnerService;
import org.spring.service.DrinkService;
import org.spring.service.FoodService;

import java.util.List;

public class SetterInjectionTest {

    DefaultBeanFactory beanFactory;
    XMLBeanDefinitionParser parser;

    @Before
    public void init(){
        beanFactory = new DefaultBeanFactory();
        parser = new XMLBeanDefinitionParser(beanFactory);
        parser.loadBeanDefinition(new ClassPathResource("restaurant.xml"));
    }
    @Test
    public void testSetterInjection(){
        DinnerService service = (DinnerService) beanFactory.getBean("dinnerService");
        Assert.assertNotNull(service);
        service.serveDinner();
        FoodService food = (FoodService) service.getFoodService();
        DrinkService drink = (DrinkService) service.getDrinkService();

        Assert.assertNotNull(food);
        food.doService();
        Assert.assertNotNull(drink);
        drink.doService();
    }

    @Test
    public void testPropertyValue(){
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("dinnerService");
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
        Assert.assertEquals(2,propertyValueList.size());

        Assert.assertTrue(this.getValueByName("foodService",propertyValueList) instanceof RuntimeReference);

        Assert.assertTrue(this.getValueByName("drinkService",propertyValueList) instanceof RuntimeReference);

        BeanDefinition food = beanFactory.getBeanDefinition("foodService");
        Assert.assertTrue(this.getValueByName("foodName",propertyValueList) instanceof TypeStringValue);


    }

    private Object getValueByName(String name,List<PropertyValue> list){
        for (PropertyValue pv : list){
            if (name.equals(pv.getName())){
                return pv.getValue();
            }
        }
        return null;
    }
}
