package service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.ProductDao;
import dao.SupplierDao;
import dao.UserDao;
import domain.Product;
import domain.Role;
import domain.Supplier;
import domain.SupplierInfoView;
import domain.User;
import service.BusinessService;
import utils.DaoFactory;
import utils.SystemUtils;

/**
 * BusinessService Implementation
 *
 * @param
 * @author Liu
 * @return
 * @exception
 */
public class BusinessServiceImpl implements BusinessService {

    private final UserDao userDao = DaoFactory.getInstance().createDao(UserDao.class); // get singleton instance
    private final ProductDao productDao = DaoFactory.getInstance().createDao(ProductDao.class);
    private final SupplierDao supplierDao = DaoFactory.getInstance().createDao(SupplierDao.class);


    /* @param
     * @return
     * @exception
     * @see service.BusinessService#isRegister(java.lang.String, java.lang.String)
     */
    @Override
    public boolean isRegister(String name, String password) {
        if (name == null || name.trim().equals("") || password.trim().equals("") ||
                password == null) {
            throw new RuntimeException("Please enter information");
        }
        List<User> list;
        try {
            list = userDao.getAllUsers();
            for (User u : list) {

                if (name.equals(u.getUserName()) && password.equals(u.getPassword())) {
                    return true;
                } else if (name.equals(u.getUserName()) && !password.equals(u.getPassword())) {
                    throw new RuntimeException("password wrong");
                }
            }
            throw new RuntimeException(name + " has not registered");
        } catch (SQLException e) {
            throw new RuntimeException("Register Unsucess");

        }


    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getUser(java.lang.String, java.lang.String)
     */
    @Override
    public User getUser(String name, String password) {

        try {
            List<User> list = userDao.getAllUsers();
            for (User u : list) {

                if (name.equals(u.getUserName()) && password.equals(u.getPassword())) {
                    return u;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("User Not Found");
        }
        return null;


    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#regiser(java.lang.String, java.lang.String)
     */
    @Override
    public void regiser(String name, String password) throws SQLException {
        if (name == null || name.trim().equals("") || password.trim().equals("") ||
                password == null) {
            throw new RuntimeException("Please enter information");
        }

        List<User> list = userDao.getAllUsers();
        for (User u : list) {
            if (name.equals(u.getUserName())) throw new RuntimeException("Name already registered.");
        }
        userDao.register(name, password);

    }


    /* @param
     * @return
     * @exception
     * @see service.BusinessService#findAllUsers()
     */
    @Override
    public List<User> findAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException("database fail");
        }
    }


    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getAllStaff()
     */
    @Override
    public List<User> getAllStaff() {
        List<User> staffs = new ArrayList<User>();

        List<User> list = findAllUsers();
        for (User u : list) {

            if (u.getRole().equals(Role.STAFF.toString())) {
                staffs.add(u);
            }
        }

        return staffs;
    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#addNewProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void addNewProduct(String name, String sum, String cost, String price, String sName, String phone) {
        try {

            Supplier s = new Supplier();
            Product pro = new Product();
            if (name.trim().equals("") || name == null || sum == null || sum.trim().equals("") || cost == null || cost.trim().equals("")
                    || price == null || price.trim().equals("") || sName == null || sName.trim().equals("")
                    || phone == null || phone.trim().equals("")) {

                throw new RuntimeException("Please fill product detail into form..");
            }
            pro.setProductName(name);

            if (isNumber(sum)) {
                pro.setQuantity(Integer.parseInt(sum));
            } else {

                throw new RuntimeException("Quantity should be number");
            }
            boolean isOk = cost.matches("^\\d{2,7}$");
            if (!isOk) {
                throw new RuntimeException("the range of Product cost is from 10 to million");
            }

            if (isNumber(cost)) {
                pro.setCostPrice(Double.parseDouble(cost));
            } else {
                throw new RuntimeException("Cost should be number");
            }
            isOk = price.matches("^\\d{2,6}$");
            if (!isOk) {
                throw new RuntimeException("the range of Product retail price is from 10 to 999999");
            }

            if (isNumber(price)) {
                pro.setSellingPrice(Double.parseDouble(price));
            } else {
                throw new RuntimeException("Retail price should be number");
            }
            s.setName(sName);

            isOk = phone.matches("^\\d{5,15}$");
            if (!isOk) {
                throw new RuntimeException("Wrong format for Phone Number(5-15)");
            }
            if (isNumber(phone)) {
                s.setPhoneNum(Integer.parseInt(phone));
            } else {
                throw new RuntimeException("Phone Number should be number");
            }
            pro.setImportDate(LocalDate.now().toString());

            productDao.addNewProduct(pro, s);

        } catch (SQLException e) {

            throw new RuntimeException("Adding new product is not successful");

        }


    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getAllProducts()
     */
    @Override
    public List<Product> getAllProducts() {

        try {
            return productDao.findAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException("Products not Found");
        }
    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getProductByName(java.lang.String)
     */
    @Override
    public List<Product> getProductByName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new RuntimeException("Please enter information");
        }

        List<Product> list;
        try {
            list = productDao.findPoductByName(name);
            if (list.isEmpty() || list == null) {
                throw new RuntimeException("Product not found. Please Search again");
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("database fails, try again");

        }


    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getProductById(java.lang.String)
     */
    @Override
    public List<Product> getProductById(String id) {

        if (id == null || id.trim().equals("")) {
            throw new RuntimeException("enter correct character");
        }
        try {
            if (isNumber(id)) {

                int inputId = Integer.parseInt(id);

                List<Product> list = productDao.findProductByID(inputId);

                if (list == null || list.isEmpty()) {

                    throw new RuntimeException("Product not found. Please Search again");
                }
                return list;
            } else {

                throw new RuntimeException("please enter number");
            }
        } catch (SQLException e) {
            throw new RuntimeException("database fails, try again");
        }

    }

    private boolean isNumber(String sum) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(sum).matches();
    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#updateRole(java.lang.String, java.lang.String)
     */
    @Override
    public void updateRole(String id, String role) {
        try {
            boolean isOk = userDao.updateRole(id, role);
            if (!isOk) {
                throw new RuntimeException("Update not Success");
            }
        } catch (SQLException e) {
            throw new RuntimeException("database fail, try again later");

        }
    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#deleteProductById(int)
     */
    @Override
    public void deleteProductById(int proID) {

        try {

            productDao.deleteProductById(proID);


        } catch (SQLException e) {
            SystemUtils.showWarning("database fail, try again later");
            throw new RuntimeException(e);

        }
    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getAllSuppliers()
     */
    @Override
    public List<Supplier> getAllSuppliers() {
        try {
            List<Supplier> list = supplierDao.findAllSuppliers();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("System can not find all suppliers");

        }

    }

    /* @param
     * @return
     * @exception
     * @see service.BusinessService#getSupplierInfos()
     */
    @Override
    public List<SupplierInfoView> getSupplierInfos() {

        try {
            List<SupplierInfoView> list = supplierDao.getSupplierInfos();
            return list;
        } catch (Exception e) {
            throw new RuntimeException("Can not find supplier info");

        }

    }


    @Override
    public void generateWeeklyReport(String monday, String sunday) {
        try {
            List<Product> list = productDao.getAllRecords(monday, sunday);
            if (list == null || list.isEmpty()) {
                throw new RuntimeException("No any records from " + monday + " to " + sunday + ". Plz search again ");

            }
            generateExcel(list);
        } catch (Exception e) {
            throw new RuntimeException("No any records, try it again");

        }

    }

    @Override
    public void generateMonthlyReport(String firstDay, String endDay) {
        try {
            List<Product> list = productDao.getAllRecords(firstDay, endDay);
            if (list == null || list.isEmpty()) {
                throw new RuntimeException("No any records. Plz search again ");

            }
            generateExcel(list);
        } catch (Exception e) {
            throw new RuntimeException("No any records. Plz search again ");
        }

    }

    private void generateExcel(List<Product> list) throws IOException {
        XSSFWorkbook wb = null;
        BufferedOutputStream out = null;
        try {
            wb = new XSSFWorkbook();  // get instance
            XSSFSheet sheet = wb.createSheet("Product Detail"); //create sheet
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ProductID");
            header.createCell(1).setCellValue("ProductName");
            header.createCell(2).setCellValue("Date");
            header.createCell(3).setCellValue("Quantity");
            header.createCell(4).setCellValue("Cost");
            header.createCell(5).setCellValue("Retail Price");
            header.createCell(6).setCellValue("Supplier Name");
            header.createCell(7).setCellValue("Supplier PhoneNumber");
            int index = 1;
            for (Product p : list) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(p.getProductID());
                row.createCell(1).setCellValue(p.getProductName());
                row.createCell(2).setCellValue(p.getImportDate());
                row.createCell(3).setCellValue(p.getQuantity());
                row.createCell(4).setCellValue(p.getCostPrice());
                row.createCell(5).setCellValue(p.getSellingPrice());
                row.createCell(6).setCellValue(p.getSupplier().getName());
                row.createCell(7).setCellValue(p.getSupplier().getPhoneNum());
                index++;
            }
            File file = new File("d:\\product.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new BufferedOutputStream(new FileOutputStream(file));
            wb.write(out);
            SystemUtils.showAlert("Success!! file is in " + file.getAbsolutePath());
        } finally {
            if (out != null) {

                out.close();
            }
            if (wb != null) {

                wb.close();
            }

        }

    }


}
