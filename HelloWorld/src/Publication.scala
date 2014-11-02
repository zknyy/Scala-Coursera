/**
 * 一个不太合理的参数化类型的例子
 */


//出版物类  
class Publication(val title: String)
//书籍类  
class Book(title: String) extends Publication(title)
//图书库类  
object Library {
  //定义图书库内所有的书籍  
  val books: Set[Book] =
    Set(
      new Book("Programming in Scala"),
      new Book("Walden"))
  //打印所有图书内容，使用外部传入的函数来实现  
  def printBookList(info: Book => AnyRef) {
    //确认Scala中一个参数的函数实际上是Function1特征的实例  
    assert(info.isInstanceOf[Function1[_, _]])
    //打印  
    for (book <- books)
      println(info(book))
  }
  //打印所有图书内容，使用外部传入的GetInfoAction特征的实例来实现  
  def printBokkListByTrait  [P >: Book, R <: AnyRef]  (action: GetInfoAction[P, R])  {
    //打印  
    for (book <- books)
      println(action(book))
  }

}
//GetInfoAction l; l(p)
//取得图书内容特征，P类型参数的类型下界是Book，R类型参数的类型上界是AnyRef  
trait GetInfoAction [P >: Book, R <: AnyRef] {
  //取得图书内容的文本描述，对应（）操作符  
  def apply(book: P): R
}


//单例对象，文件的主程序  
object Customer extends Application {
  //定义取得出版物标题的函数  
  def getTitle(p: Publication): String = p.title
  //使用函数来打印  
  Library.printBookList(getTitle)

  //使用特征GetInfoAction的实例来打印  
  Library.printBokkListByTrait(new GetInfoAction[Publication, String] {
    def apply(p: Publication): String = p.title
  })
}  


