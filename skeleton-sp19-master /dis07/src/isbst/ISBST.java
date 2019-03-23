package isbst;

public class ISBST {

    public static boolean buggyIsBST(Treenode T){
        if(T ==null){
            return true;
        }
        else if(T.left != null && T.left.val > T.val){
            return false;
        }
        else if(T.right != null && T.right.val < T.val){
            return false;
        }
        else{
            return buggyIsBST(T.left) && buggyIsBST(T.right);
        }
    }


    public static boolean isBST(Treenode T){
        return isBSTHelper(T,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }


    public static boolean isBSTHelper(Treenode T,int min,int max){
        if(T == null){
            return true;
        }
        else if(T.val < min || T.val > max ){
            return false;
        }
        else {
            return (isBSTHelper(T.left,min,T.val) && isBSTHelper(T.right,T.val,max));

        }

    }





    public static void main(String[] args){
        Treenode a1 = new Treenode(2,null,null);
        Treenode a2 = new Treenode(4,null,null);
        Treenode b1 = new Treenode(6,null,null);
        Treenode b2 = new Treenode(8,null,null);
        Treenode a = new Treenode(3,a1,a2);
        Treenode b = new Treenode(7,b1,b2);
        Treenode t = new Treenode(5,a,b);

        ISBST test = new ISBST();
        System.out.println(test.isBST(t));


    }


}
