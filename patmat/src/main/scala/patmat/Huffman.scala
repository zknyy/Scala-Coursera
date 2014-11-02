package patmat

import common._

/**
 * Assignment 4: Huffman coding
 *
 */
object Huffman {

  /**
   * A huffman code is represented by a binary tree.
   *
   * Every `Leaf` node of the tree represents one character of the alphabet that the tree can encode.
   * The weight of a `Leaf` is the frequency of appearance of the character.
   *
   * The branches of the huffman tree, the `Fork` nodes, represent a set containing all the characters
   * present in the leaves below it. The weight of a `Fork` node is the sum of the weights of these
   * leaves.
   */
  abstract class CodeTree
  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
  case class Leaf(char: Char, weight: Int) extends CodeTree

  // Part 1: Basics

  def weight(tree: CodeTree): Int = tree match {
    case Fork(l, r, c, w) => w
    case Leaf(c, w) => w
  }

  def chars(tree: CodeTree): List[Char] = tree match {
    case Fork(l, r, c, w) => c
    case Leaf(c, w) => List(c)
  }

  def makeCodeTree(left: CodeTree, right: CodeTree) =
    Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

  // Part 2: Generating Huffman trees

  /**
   * In this assignment, we are working with lists of characters. This function allows
   * you to easily create a character list from a given string.
   */
  def string2Chars(str: String): List[Char] = str.toList

  /**
   * This function computes for each unique character in the list `chars` the number of
   * times it occurs. For example, the invocation
   *
   *   times(List('a', 'b', 'a'))
   *
   * should return the following (the order of the resulting list is not important):
   *
   *   List(('a', 2), ('b', 1))
   *
   * The type `List[(Char, Int)]` denotes a list of pairs, where each pair consists of a
   * character and an integer. Pairs can be constructed easily using parentheses:
   *
   *   val pair: (Char, Int) = ('c', 1)
   *
   * In order to access the two elements of a pair, you can use the accessors `_1` and `_2`:
   *
   *   val theChar = pair._1
   *   val theInt  = pair._2
   *
   * Another way to deconstruct a pair is using pattern matching:
   *
   *   pair match {
   *     case (theChar, theInt) =>
   *       println("character is: "+ theChar)
   *       println("integer is  : "+ theInt)
   *   }
   */
  def times(chars: List[Char]): List[(Char, Int)] = {
    val empty: List[(Char, Int)] = List();

    //ͨ���ۻ�������list
    def timesHelper(chars: List[Char], acc: List[(Char, Int)]): List[(Char, Int)] = {
      //�õ���ĸ���ڵ�λ�ú������еļ���ֵ
      //���Ϊ���ַ�(���������������б���),��λ��Ϊ-1
      def getIndexAndCounter(innerc: Char, acc: List[(Char, Int)]): (Int, Int) = {
        if (!acc.isEmpty)
          for (i <- 0 to acc.size - 1) {
            if (acc(i) != Nil)
              if (acc(i)._1 == innerc) return (i, acc(i)._2)
          }
        (-1, 0)
      }

      def removeElem[T >: Nothing](i: Int, l: List[T]): List[T] = {
        l.take(i) ::: l.takeRight(l.size - i - 1)
      }

      if (chars.size == 0) acc //���û���ַ����ʾ�ۻ��������
      else {
        val c = chars.head
        val index_count = getIndexAndCounter(c, acc);
        //���Ϊ���ַ�(���������������б���)	
        if (index_count._1 < 0) {
          //������c-iԪ��
          val newElem: (Char, Int) = (c, 1)
          //������һ�ε���(ȥ����ǰ��ĸ֮�����ĸ�б�,����Ԫ��׷�ӵ�ԭ����acc��)
          timesHelper(chars.tail, newElem :: acc);
        } //����б����Ѿ����ڵ�ǰ��ĸ,����Ҫ�������ֵ+1
        else {
          val newCount = index_count._2
          val newElem = (c, newCount + 1)
          //ȥ��ԭ����,��׷��һ����Ԫ��(�������ֵ+1)
          val newAcc = newElem :: removeElem(index_count._1, acc)
          //��������
          timesHelper(chars.tail, newAcc);
        }
      }
    }
    timesHelper(chars, empty)
  }

  /**
   * Returns a list of `Leaf` nodes for a given frequency table `freqs`.
   *
   * The returned list should be ordered by ascending weights (i.e. the
   * head of the list should have the smallest weight), where the weight
   * of a leaf is the frequency of the character.
   */
  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {
    //�Ƚ�������
    val f = freqs.sortWith((s, t) => s._2.compareTo(t._2) < 0)
    //ʹ���ۻ������������
    val empty = List[Leaf]();
    def helper(freqs: List[(Char, Int)], acc: List[Leaf]): List[Leaf] = {
      //�����ת���б�Ϊ����ת�����
      if (freqs.isEmpty) acc
      else {
        //����Ԫ��ת��
        val elem = freqs.head
        //����������Ԫ��׷�ӵ��ۻ��б���
        val newAcc = acc :+ new Leaf(elem._1, elem._2)
        val newFreqs = freqs.tail
        //����,����ʣ�µ�Ԫ��ת��
        helper(newFreqs, newAcc)
      }
    }
    helper(f, empty)
  }

  /**
   * Checks whether the list `trees` contains only one single code tree.
   */
  def singleton(trees: List[CodeTree]): Boolean = {
    if (!trees.isEmpty && trees.size == 1) true
    else false
    /*
    //��ĳ�����ڵ���
    def check(n: CodeTree): Boolean = {
      //����֧���
      if (n.isInstanceOf[Fork]) {
        val f = n.asInstanceOf[Fork]
        if (f.weight != weight(f.left) + weight(f.right)) return false
        else {
          //�������ҷ�֧���
          if (check(f.left) == false) return false
          if (check(f.right) == false) return false
        }
      }
      //      else {
      //
      //      }     
      true
    }
    for (i <- 0 to trees.size - 1) {
      if (check(trees(i)) == false) return false
    }
    true
    */
  }

  /**
   * The parameter `trees` of this function is a list of code trees ordered
   * by ascending weights.
   *
   * This function takes the first two elements of the list `trees` and combines
   * them into a single `Fork` node. This node is then added back into the
   * remaining elements of `trees` at a position such that the ordering by weights
   * is preserved.
   *
   * If `trees` is a list of less than two elements, that list should be returned
   * unchanged.
   */
  def combine(trees: List[CodeTree]): List[CodeTree] = {
    if (trees == Nil) List[CodeTree]()
    else if (trees.size == 1) trees
    else if (trees.size == 1) trees
//    if (trees.size == 1) return trees
    else {
      val left = trees.head
      val right = trees.tail.head;
      val newFork = makeCodeTree(left, right)
      //      val f = new Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

      val newList = newFork :: trees.tail.tail
      //order
      newList.sortWith((s, t) => weight(s).compareTo(weight(t)) < 0)

    }
  }

  /**
   * This function will be called in the following way:
   *
   *   until(singleton, combine)(trees)
   *
   * where `trees` is of type `List[CodeTree]`, `singleton` and `combine` refer to
   * the two functions defined above.
   *
   * In such an invocation, `until` should call the two functions until the list of
   * code trees contains only one single tree, and then return that singleton list.
   *
   * Hint: before writing the implementation,
   *  - start by defining the parameter types such that the above example invocation
   *    is valid. The parameter types of `until` should match the argument types of
   *    the example invocation. Also define the return type of the `until` function.
   *  - try to find sensible parameter names for `xxx`, `yyy` and `zzz`.
   */
  def until(s: List[CodeTree] => Boolean,
    c: List[CodeTree] => List[CodeTree])(t: List[CodeTree]): List[CodeTree] = {
    if (s(t)) t
    else {
      until(s, c)(c(t))
    }
  }

  /**
   * This function creates a code tree which is optimal to encode the text `chars`.
   *
   * The parameter `chars` is an arbitrary text. This function extracts the character
   * frequencies from that text and creates a code tree based on them.
   */
  def createCodeTree(chars: List[Char]): CodeTree = {
    val leaves = makeOrderedLeafList(times(chars))
    until(singleton, combine)(leaves).head
  }

  // Part 3: Decoding

  type Bit = Int

  /**
   * This function decodes the bit sequence `bits` using the code tree `tree` and returns
   * the resulting list of characters.
   */
  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {
    val empty = List[Char]()
    //��������������Ҫ��¼��������:
    //��ǰ�Ľ����ڵ�currentNode, �ۻ��Ľ����ַ���acc
    def decodeHelper(bits: List[Bit], currentNode: CodeTree, acc: List[Char]): List[Char] = {
      //������������� ���� ��֧�ڵ���,�򷵻ص�ǰ�Ļ����ַ����б�Ϊ���ս��
      if (bits.isEmpty && currentNode.isInstanceOf[Fork]) acc
      //��֧���
      else if (currentNode.isInstanceOf[Fork]) {
        //��0,��1
        if (bits.head == 0) decodeHelper(bits.tail, currentNode.asInstanceOf[Fork].left, acc)
        else decodeHelper(bits.tail, currentNode.asInstanceOf[Fork].right, acc)
      } else {
        decodeHelper(bits, tree, acc ::: chars(currentNode))
      }
    }

    decodeHelper(bits, tree, empty)
  }

  /**
   * A Huffman coding tree for the French language.
   * Generated from the data given at
   *   http://fr.wikipedia.org/wiki/Fr%C3%A9quence_d%27apparition_des_lettres_en_fran%C3%A7ais
   */
  val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s', 121895), Fork(Leaf('d', 56269), Fork(Fork(Fork(Leaf('x', 5928), Leaf('j', 8351), List('x', 'j'), 14279), Leaf('f', 16351), List('x', 'j', 'f'), 30630), Fork(Fork(Fork(Fork(Leaf('z', 2093), Fork(Leaf('k', 745), Leaf('w', 1747), List('k', 'w'), 2492), List('z', 'k', 'w'), 4585), Leaf('y', 4725), List('z', 'k', 'w', 'y'), 9310), Leaf('h', 11298), List('z', 'k', 'w', 'y', 'h'), 20608), Leaf('q', 20889), List('z', 'k', 'w', 'y', 'h', 'q'), 41497), List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127), List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291), Fork(Fork(Leaf('o', 82762), Leaf('l', 83668), List('o', 'l'), 166430), Fork(Fork(Leaf('m', 45521), Leaf('p', 46335), List('m', 'p'), 91856), Leaf('u', 96785), List('m', 'p', 'u'), 188641), List('o', 'l', 'm', 'p', 'u'), 355071), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362), Fork(Fork(Fork(Leaf('r', 100500), Fork(Leaf('c', 50003), Fork(Leaf('v', 24975), Fork(Leaf('g', 13288), Leaf('b', 13822), List('g', 'b'), 27110), List('v', 'g', 'b'), 52085), List('c', 'v', 'g', 'b'), 102088), List('r', 'c', 'v', 'g', 'b'), 202588), Fork(Leaf('n', 108812), Leaf('t', 111103), List('n', 't'), 219915), List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503), Fork(Leaf('e', 225947), Fork(Leaf('i', 115465), Leaf('a', 117110), List('i', 'a'), 232575), List('e', 'i', 'a'), 458522), List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)

  /**
   * What does the secret message say? Can you decode it?
   * For the decoding use the `frenchCode' Huffman tree defined above.
   */
  val secret: List[Bit] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  /**
   * Write a function that returns the decoded secret
   */
  def decodedSecret: List[Char] = decode(frenchCode, secret)

  // Part 4a: Encoding using Huffman tree

  /**
   * This function encodes `text` using the code tree `tree`
   * into a sequence of bits.
   */
  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    //���, �ַ����б�Ϊ��,�򷵻ػ��۱����б�
    //ÿ�γ������б��еĵ�һ���ַ�(Ŀ���ַ�)�������²���
    //�ֱ��ڵ�ǰ�ڵ�� ���� �����ڵ��е��ַ�����Ѱ�� Ŀ���ַ�
    //��� ������[��]��[��],
    //		���,���ӽڵ��Ƿ�֧���,��������
    //		���,���ӽڵ���Ҷ�ӽڵ�,��ʼ��һ���ַ�,�Ӹ��ڵ㿪ʼ����    
    def encodeHelper(text: List[Char], currentNode: CodeTree, acc: List[Bit]): List[Bit] = {
      if (text.isEmpty) acc
      else {
        val c = text.head
        //        val cList = chars(currentNode)
        //��֧���,��������
        if (currentNode.isInstanceOf[Fork]) {
          val left = currentNode.asInstanceOf[Fork].left
          val right = currentNode.asInstanceOf[Fork].right
          //��0,��1
          if (chars(left).contains(c)) {
            //���������
            encodeHelper(text, left, acc :+ 0)
          } else {
            //�������ұ�
            encodeHelper(text, right, acc :+ 1)
          }
        } else {
          //Ҷ�ӽڵ�,��ʼ��һ���ַ�,�Ӹ��ڵ㿪ʼ����
          encodeHelper(text.tail, tree, acc)
        }
      }
    }
    encodeHelper(text, tree, List[Bit]())
  }

  // Part 4b: Encoding using code table

  type CodeTable = List[(Char, List[Bit])]

  /**
   * This function returns the bit sequence that represents the character `char` in
   * the code table `table`.
   */
  def codeBits(table: CodeTable)(char: Char): List[Bit] = {
    //ʹ��ѭ���ҳ��ڵ�
    for (i <- 0 to table.size - 1) {
      if (table(i)._1 == char) return table(i)._2
    }
    List[Bit]()
    //    new Error
  }

  /**
   * Given a code tree, create a code table which contains, for every character in the
   * code tree, the sequence of bits representing that character.
   *
   * Hint: think of a recursive solution: every sub-tree of the code tree `tree` is itself
   * a valid code tree that can be represented as a code table. Using the code tables of the
   * sub-trees, think of how to build the code table for the entire tree.
   */
  def convert(tree: CodeTree): CodeTable = {
    //�Ӹ��ڵ㿪ʼ���·ֽ�
    //�ֱ��ж������ӽڵ�,�����������:
    //	1.��֧�ڵ�,��������
    //	2.Ҷ�ӽڵ�,����,����accTable
    def convertHelper(reduceTree: CodeTree, accBit: List[Bit], accTable: CodeTable): CodeTable = reduceTree match {
      case Fork(l, r, c, w) => {
        //��0,��1,�ϲ�����֮��ת���������ַ���
        mergeCodeTables(convertHelper(l, accBit :+ 0, accTable), convertHelper(r, accBit :+ 1, accTable))
      }
      case Leaf(c, w) => {
        //        //�ѵ�ǰҶ�ӽڵ����ַ���ϲ�
        //        val newCodeTable = List[(Char, List[Bit])]((c, accBit))
        //        mergeCodeTables(accTable, newCodeTable)
        //ֱ�Ӱѵ�ǰҶ�ӽڵ����ַ����ϲ�
        accTable :+ (c, accBit)
      }
    }
    convertHelper(tree, List[Bit](), List[(Char, List[Bit])]())
  }

  /**
   * This function takes two code tables and merges them into one. Depending on how you
   * use it in the `convert` method above, this merge method might also do some transformations
   * on the two parameter code tables.
   */
  def mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = a ::: b

  /**
   * This function encodes `text` according to the code tree `tree`.
   *
   * To speed up the encoding process, it first converts the code tree to a code table
   * and then uses it to perform the actual encoding.
   */
  def quickEncode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    val table = convert(tree)
    def quickEncodeHelper(reduceText: List[Char], acc: List[Bit]): List[Bit] = {
      if (reduceText.isEmpty) acc
      else {
        val c = reduceText.head
        val code = codeBits(table)(c)
        quickEncodeHelper(reduceText.tail, acc:::code)
      }
    }
    quickEncodeHelper(text, List[Bit]())
  }
}
