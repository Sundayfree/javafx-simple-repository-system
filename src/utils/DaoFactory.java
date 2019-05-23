package utils;


import java.util.Properties;


/**
 * Factory class for creating Dao instance
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class DaoFactory {
    private static Properties prop = new Properties();

    private DaoFactory() {
        try {// read property file and load it
            prop.load(DaoFactory.class.getClassLoader().getResourceAsStream("daoFactory.property"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ;
    private static final DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }


    @SuppressWarnings("unchecked")
    public <T> T createDao(Class<T> daoInter) {
        try {
            String key = daoInter.getSimpleName(); //to get interface name
            String className = prop.getProperty(key);
            return (T) Class.forName(className).newInstance(); // create instance
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
