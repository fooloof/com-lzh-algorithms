package dpproblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lzh
 * @Date: 2018/11/25 19:11
 * @Description:
 */
public class BackPackMain {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("手机",1,2));
        items.add(new Item("电脑",4,5));
        items.add(new Item("音响",3,4));
        int itemCount = items.size()+1;
        int totalWeight = 5;
        BackPack backPacks[][] = new BackPack[itemCount][totalWeight];
        //当商品数量为0的时候，初始化每个背包
        for (int j = 1; j < totalWeight; j++) {
            backPacks[0][j] =  new BackPack(j);
        }

       /** 要不把新遍历的包裹装下，要不不装*/
        for (int i = 0; i < itemCount; i++) {
            Item item = items.get(i);
            for (int j = 1; j < totalWeight; j++) {
                System.out.println("1111111"+i+j);

                Integer backPackWeight = j;
                Integer itemWeight = item.getWeight();
                if(backPackWeight < itemWeight)continue;  //包裹装不下商品
                if(backPackWeight == itemWeight){//如果一样重
                    Integer itemPrice = item.getPrice();
                    Integer backPackPrice = backPacks[i][j].getPrice();
                    if(itemPrice>=backPackPrice){//新的商品价格大于以前包裹已经存放的价格
                        backPacks[i+1][j] = new BackPack(j);
                        ArrayList<Item> newItems = new ArrayList<Item>();//因为一样重，所以只放只一个商品
                        newItems.add(item);
                        backPacks[i][j].setItems(newItems);
                        backPacks[i][j].setPrice(item.getPrice());
                    }
                }else {
                    Integer backPackWeight1 = backPackWeight - itemWeight;//剩余空间
                    BackPack backPack1 = backPacks[i][backPackWeight1];//这个空间原有的包裹
                    //如果把这个物品放到包裹后的价格
                    Integer itemPrice = backPack1.getPrice()+item.getPrice();
                    //包裹原来的价格
                    Integer backPackPrice = backPacks[i][j].getPrice();
                    if(itemPrice >= backPackPrice  ){
                        backPacks[i+1][j] = new BackPack(j);
                        List<Item> items1 = backPack1.getItems();
                        items1.add(item);
                        backPacks[i+1][j].setItems(items1);
                        backPacks[i+1][j].setPrice(itemPrice);
                    }
                }
            }
        }
        System.out.println(backPacks[itemCount][totalWeight]);

    }
    static class  BackPack{
        List<Item> items = new ArrayList<Item>();
        Integer Price = 0;
        Integer weight;

        public BackPack(Integer weight) {
            this.weight = weight;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public Integer getPrice() {
            return Price;
        }

        public void setPrice(Integer price) {
            Price = price;
        }

        @Override
        public String toString() {
            return "BackPack{" +
                    "包裹商品items=" + items +
                    ",包裹总价格 Price=" + Price +
                    ", 包裹总重量weight=" + weight +
                    '}';
        }
    }


    static class Item{
        String name;
        Integer weight;
        Integer Price;

        public Item(String name, Integer weight, Integer price) {
            this.name = name;
            this.weight = weight;
            Price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getPrice() {
            return Price;
        }

        public void setPrice(Integer price) {
            Price = price;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "商品名称name='" + name + '\'' +
                    ", 商品重量weight=" + weight +
                    ", 商品价格Price=" + Price +
                    '}';
        }
    }



}
