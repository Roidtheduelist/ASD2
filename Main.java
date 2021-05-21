package com.company;
import javax.swing.text.html.HTML;

import java.util.*;
class node{
    String data;
    node kiri;
    node kanan;
    node id;
    node text;
    node parent;

    public node(String data) {
        this.data=data;
        kiri=null;
        kanan=null;
        this.parent=null;
        this.id=null;
        this.text=null;
    }
    public node(String data,node id,node parent) {
        this.data=data;
        kiri=null;
        kanan=null;
        this.parent=parent;
        this.id=id;
        this.text=null;
    }

    public void insertid(String id){
        this.id = new node(id);
    }
    public void insertText(String data){
        this.text=new node(data);
    }
    public void setParent(node selector){
        this.parent = selector;}
    public void setKiri(node data){
        this.kiri=data;
    }
    public void setKanan(node data){
        this.kanan=data;
    }
}
class tree{
    node root;
    boolean checkin=false;
    void tree(){
        root=null;
    }
    public void sisip(program masukan){
       node tmp=root;
        if (root==null){
           root=new node(masukan.selector);
           root.insertid(masukan.selector);
           root.kiri=new node(masukan.tag);
           root.kiri.insertid(masukan.id);
        }else if (masukan.text!=null){
            masukinteks(masukan);
        }else if (tmp.id.data!=masukan.selector.substring(1)) {
            masukin(new node(masukan.tag,new node(masukan.id),new node(masukan.selector.substring(1))));
        }
    }
    public void hapus(String selector){
        deleted(root,selector);

    }
    public void deleted(node tmp,String selector){
        if (tmp==null)return;
        if (tmp.data.equals(selector)){
            banish(tmp);
        }
        deleted(tmp.kiri,selector);
        deleted(tmp.kanan,selector);
    }
    public void banish(node tmp){
        if (tmp==null)return;
        banish(tmp.kiri);
        banish(tmp.kanan);
        System.out.println("node "+tmp.data+" dihapus");
        if (tmp.parent!=null){
        if (tmp.parent.kiri==tmp) {
            tmp.parent.kiri = null;
            tmp.id=null;
            tmp.text=null;
            tmp.data=null;
        }else {
            tmp.parent.kanan = null;
            tmp.id=null;
            tmp.text=null;
            tmp.data=null;
        }}
    }
    public void print(String selector,String maxdepth){

    }
    public void search(String selector){
       insertsearch(root,selector);
       if (checkin){

       }else System.out.println(selector+" tidak ditemukan");

    }
    public void insertsearch(node tmp,String suhu){
        if (tmp==null)return;
        if (tmp.data.equals(suhu)){
            System.out.println(suhu+" ditemukan");
            checkin=true;
            return;
        }
        insertsearch(tmp.kiri,suhu);
        insertsearch(tmp.kanan,suhu);

    }

    public void masukinteks(program x){
        teksdisini(x,root);
    }
    public void teksdisini(program teks, node tmp){
        if (tmp==null)return;
        if (teks.tag.substring(1)!=tmp.id.data){
            tmp.insertText(teks.text);
        }
        teksdisini(teks,tmp.kiri);
        teksdisini(teks,tmp.kanan);
    }
    public void print(){
        print(root);
    }
    public void print(node x){
        if (x==null)return;
        node tmp=x;
        print(tmp.kiri);
        if (x.text!=null)
        System.out.println(tmp.data+tmp.id.data+tmp.text.data);
        System.out.println(tmp.data+tmp.id.data);
        print(tmp.kanan);
    }
    public void masukin(node masuk){
      masukinbang(masuk,root);

    }
    public void masukinbang(node masukan,node tmp){

        if (masukan==null){
            System.out.println("awal null");
            return;
        }
        if (tmp==null){
            return;
        }
        if (tmp.data.equals(masukan.parent.data)){
            if (tmp.kiri!=null&&tmp.kanan!=null){
                if (tmp.kanan.kanan!=null){
                tmp.kiri.setParent(new node("skip",new node("skip"),tmp));
                tmp.kiri.parent.setKiri(tmp.kiri);
                tmp.setKiri(tmp.kiri.parent);
                tmp.kiri.setKanan(tmp.kanan);
                tmp.setKanan(masukan);}
            }
            else if (tmp.kiri!=null)
                tmp.setKanan(masukan);
            else tmp.setKiri(masukan);
            masukan.setParent(tmp);
        }
        masukinbang(masukan,tmp.kiri);
        masukinbang(masukan,tmp.kanan);

    }
}
class program{
    String selector,tag,id,text=null;
    public program(String selector, String tag, String id) {
        if (selector.toLowerCase().equals("text")){
            this.text=id;
            this.selector=tag;
        }else
        this.selector=selector;

        this.tag=tag;
        this.id=id;
    }


}
public class Main {

    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	tree pohon = new tree();
	String[] cmd=new String[50];

	int test=5;
	for (int i=0;i <test;i++){
	    cmd[i]=input.nextLine();
	    String[] splitting=cmd[i].split(";");
	    switch (splitting[0].toLowerCase()){
            case "add": if (splitting.length!=4){
                System.out.println("boundary error");return;}pohon.sisip(new program(splitting[1],splitting[2],splitting[3]));break;
            case "delete":if (splitting.length!=4){
                System.out.println("boundary error");return;};
            case "search":if (splitting.length!=4){
                System.out.println("boundary error");return;};
            case "print":if (splitting.length!=1){
                System.out.println("boundary error");return;}pohon.print();break;

        }
    }
    }
}
