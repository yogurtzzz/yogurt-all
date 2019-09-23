package sound;

import org.springframework.beans.factory.BeanNameAware;

public class JayCD implements CD, BeanNameAware {
    private String beanName;
    public String getCDName() {
        return "彩虹";
    }

    public String getAuthor() {
        return "周杰伦";
    }

    @Override
    public String getBeanName() {
        return this.beanName;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }
}
