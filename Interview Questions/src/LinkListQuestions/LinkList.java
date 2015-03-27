package LinkListQuestions;

public class LinkList {
	private int data;
	private LinkList next;
	public LinkList(int data){
		this.data=data;
	}
	public int getData(){return data;}
	public LinkList getNext(){return next;}
	public void setData(int data){this.data=data;}
	public void setNext(LinkList next){this.next= next;}
}
