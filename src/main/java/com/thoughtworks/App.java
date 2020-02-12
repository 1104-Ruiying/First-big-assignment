package com.thoughtworks;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    String selectedItemsNew =selectedItems.replace(" ", "") ;
    String [] splitItems = selectedItemsNew.split("x|,");
    String [] names = new String[splitItems.length];
    double [] price = new double[splitItems.length];
    for (int i = 0; i < splitItems.length; i+=2) {
      if(splitItems[i].equals(getItemIds()[0])){
        names[i] = getItemNames()[0];//把黄焖鸡这个名字给到names数组的第一个位置
        price[i] = getItemPrices()[0];
      }else if (splitItems[i].equals(getItemIds()[1])){
        names[i] = getItemNames()[1];//把黄焖鸡这个名字给到names数组的第一个位置
        price[i] = getItemPrices()[1];
      }else if (splitItems[i].equals(getItemIds()[2])){
        names[i] = getItemNames()[2];//把黄焖鸡这个名字给到names数组的第一个位置
        price[i] = getItemPrices()[2];
      }else if (splitItems[i].equals(getItemIds()[3])){
        names[i] = getItemNames()[3];//把黄焖鸡这个名字给到names数组的第一个位置
        price[i] = getItemPrices()[3];
      }
    }

    String detail = "============= 订餐明细 ============="+ "\n";
    for (int i = 0; i < splitItems.length; i+=2) {
      detail+= names[i] + " x " + splitItems[i+1] + " = " + (int)(price[i] * Double.parseDouble(splitItems[i+1])) + "元" +"\n";
    }
    int totalPrice1=0;
    int totalPrice2=0;
    int totalPrice3=0;

    //原价
    for (int i = 0; i < splitItems.length; i+=2) {
      totalPrice1 += (int)(price[i] * (Double.parseDouble(splitItems[i+1])));
    }
    String result1 =detail +
            "-----------------------------------\n" +
            "总计："+totalPrice1+ "元" +"\n" +
            "===================================";

    //满30-6
    if(totalPrice1 >= 30){
      totalPrice2 = totalPrice1 - 6;
    }

    String result2 = detail +
            "-----------------------------------\n" +
            "使用优惠:\n" +
            "满30减6元，省6元\n" +
            "-----------------------------------\n" +
            "总计："+ totalPrice2 + "元" +"\n" +
            "===================================";
    //半价
    int halfPrice=0 ;
    for (int i = 0; i < splitItems.length; i+=2) {
      if(splitItems[i].equals(getHalfPriceIds()[0])){
        price[i] = getItemPrices()[0] / 2;
        halfPrice= (int) price[i]* (int) (Double.parseDouble(splitItems[i+1]));
      }
      if (splitItems[i].equals(getHalfPriceIds()[1])){
        price[i] = getItemPrices()[2] /2;
        halfPrice+=(int)price[i]*(int)Double.parseDouble(splitItems[i+1]);
      }
    }
    for (int i = 0; i < splitItems.length; i+=2) {
      totalPrice3 += price[i] * Double.parseDouble(splitItems[i+1]);
    }
    String result3 = detail +
            "-----------------------------------\n" +
            "使用优惠:\n" +
            "指定菜品半价(黄焖鸡，凉皮)，省"+halfPrice+"元"+"\n" +
            "-----------------------------------\n" +
            "总计："+totalPrice3+ "元" +"\n" +
            "===================================";

    String result;
    if(totalPrice1 >= 30){
      if(totalPrice2 > totalPrice3){
        result = result3;
      }else{
        result = result2;
      }
    }else{
      if (totalPrice1>totalPrice3){
        result = result3;
      }else{
        result = result1;
      }
    }
    return result;
  }
//={ITEM0001 1  ITEM0013 2}
  /**
   * 获取每个菜品依次的编号
   */
  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }

  /**
   * 获取每个菜品依次的名称
   */
  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  /**
   * 获取每个菜品依次的价格
   */
  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  /**
   * 获取半价菜品的编号
   */
  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }
}
