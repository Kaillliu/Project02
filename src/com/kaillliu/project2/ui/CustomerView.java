package com.kaillliu.project2.ui;

import com.kaillliu.project2.bean.Customer;
import com.kaillliu.project2.service.CustomerList;
import com.kaillliu.project2.util.CMUtility;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @author Kaillliu
 * @date 2021/8/6
 * @time 11:00
 */


public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    /**
     * 用途：显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理。
     */
    public void enterMainMenu(){

        boolean isFlag = true ;
        while (isFlag){
            System.out.println("-----------------客户信息管理软件-----------------");
            System.out.println("                 1 添 加 客 户");
            System.out.println("                 2 修 改 客 户");
            System.out.println("                 3 删 除 客 户");
            System.out.println("                 4 客 户 列 表");
            System.out.println("                 5 退      出");
            System.out.println("请选择(1-5)：");

            char memu = CMUtility.readMenuSelection();
            switch (memu){
                case '1':addNewCustomer();break;
                case '2':modifyCustomer();break;
                case '3':deleteCustomer();break;
                case '4':listAllCustomers();break;
                case '5':
                    System.out.println("是否确认退出(Y/N): ");
                    char isExitj = CMUtility.readConfirmSelection();
                    if (isExitj == 'Y') {
                        isFlag = false;
                    }
            }


        }
        //isFlag = false;
    }

    /**
     * 用途：这四个方法分别完成“添加客户”、“修改客户”、“删除客户”和“客户列表”等各菜单功能。
     * 	这四个方法仅供enterMainMenu()方法调用
     */
    private void addNewCustomer(){
        System.out.println("---------------------添加客户---------------------\n");
        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        //创建对象
        Customer customer = new Customer(name,gender,age,phone,email);
        boolean isSuccess =  customerList.addCustomer(customer);
        if (isSuccess){
            System.out.println("---------------------添加完成---------------------");
        }else {
            System.out.println("----------------客户目录已满,添加失败---------------");
        }

    }
    private void modifyCustomer(){
        System.out.println("---------------------修改客户---------------------\n");
        Customer cust;
        int number;
        for (;;){
            System.out.println("请选择待修改客户编号(-1退出)：");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            cust = customerList.getCustomer(number-1);
            if(cust == null){
                System.out.println("无法找到指定客户");
            }else{
                break;
            }
        }
        //修改客户信息
        System.out.println("姓名（"+cust.getName()+"):");
        String name = CMUtility.readString(10,cust.getName());
        System.out.println("性别（"+cust.getGender()+"):");
        char gender = CMUtility.readChar(cust.getGender());
        System.out.println("年龄（"+cust.getAge()+"):");
        int age = CMUtility.readInt(cust.getAge());
        System.out.println("电话（"+cust.getPhone()+"):");
        String phone = CMUtility.readString(13,cust.getPhone());
        System.out.println("邮箱（"+cust.getEmail()+"):");
        String email = CMUtility.readString(30,cust.getEmail());

        Customer netCust = new  Customer(name,gender,age,phone,email);
        boolean isRepalaced =  customerList.replaceCustomer(number-1,netCust);
        if (isRepalaced) {
            System.out.println("---------------------------修改成功---------------------------");
        }else {
            System.out.println("---------------------------修改失败---------------------------");
        }

    }


    private void deleteCustomer(){
        System.out.println("---------------------------删除客户---------------------------");
        System.out.println("请选择待修改客户编号(-1退出)：");
        Customer cust;
        int number;
        for (;;){
            System.out.println("请选择待修改客户编号(-1退出)：");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            cust = customerList.getCustomer(number-1);
            if(cust == null){
                System.out.println("无法找到指定客户");
            }else{
                break;
            }
        }
        System.out.println("是否确认删除(Y/N):");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'Y') {
            boolean deleteSuccess = customerList.deleteCustomer(number-1);
            if (deleteSuccess ) {
                System.out.println("---------------------------删除成功---------------------------");
            }else {
                System.out.println("---------------------------删除失败---------------------------");
            }
        }else {
            return;
        }


    }



    private void listAllCustomers(){
        int total = customerList.getTotal();
        if (total ==0){
            System.out.println("没有客户记录");
        }else {
            System.out.println("---------------------------客户列表---------------------------");
            System.out.println("编号\t姓名\t\t性别\t年龄\t电话\t\t\t邮箱");
           Customer[] custs = customerList.getAllCustomers();
            for (int i = 0; i < custs.length; i++) {
                Customer cust = custs[i];
                System.out.println((i+1)+"\t"+cust.getName()+"\t"+cust.getGender()+"\t"+cust.getAge()+"\t"+cust.getPhone()+"\t"+cust.getEmail() );
            }
            System.out.println("-------------------------客户列表完成-------------------------");

        }

    }




    //用途：创建CustomerView实例，并调用 enterMainMenu()方法以执行程序。
    public static void main(String[] args){
        CustomerView view = new CustomerView();
        view.enterMainMenu();

    }




}
