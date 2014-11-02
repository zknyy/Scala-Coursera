/**
 * һ����̫����Ĳ��������͵�����
 */


//��������  
class Publication(val title: String)
//�鼮��  
class Book(title: String) extends Publication(title)
//ͼ�����  
object Library {
  //����ͼ��������е��鼮  
  val books: Set[Book] =
    Set(
      new Book("Programming in Scala"),
      new Book("Walden"))
  //��ӡ����ͼ�����ݣ�ʹ���ⲿ����ĺ�����ʵ��  
  def printBookList(info: Book => AnyRef) {
    //ȷ��Scala��һ�������ĺ���ʵ������Function1������ʵ��  
    assert(info.isInstanceOf[Function1[_, _]])
    //��ӡ  
    for (book <- books)
      println(info(book))
  }
  //��ӡ����ͼ�����ݣ�ʹ���ⲿ�����GetInfoAction������ʵ����ʵ��  
  def printBokkListByTrait  [P >: Book, R <: AnyRef]  (action: GetInfoAction[P, R])  {
    //��ӡ  
    for (book <- books)
      println(action(book))
  }

}
//GetInfoAction l; l(p)
//ȡ��ͼ������������P���Ͳ����������½���Book��R���Ͳ����������Ͻ���AnyRef  
trait GetInfoAction [P >: Book, R <: AnyRef] {
  //ȡ��ͼ�����ݵ��ı���������Ӧ����������  
  def apply(book: P): R
}


//���������ļ���������  
object Customer extends Application {
  //����ȡ�ó��������ĺ���  
  def getTitle(p: Publication): String = p.title
  //ʹ�ú�������ӡ  
  Library.printBookList(getTitle)

  //ʹ������GetInfoAction��ʵ������ӡ  
  Library.printBokkListByTrait(new GetInfoAction[Publication, String] {
    def apply(p: Publication): String = p.title
  })
}  


