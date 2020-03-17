package uk.adthena.shoppingbasket

import uk.adthena.shoppingbasket.ItemType.ItemType

object ItemType extends Enumeration {
  type ItemType = Value
  val Soup, Bread, Milk, Apple = Value
  def withNameOpt(s: String): Option[ItemType] = values.find(_.toString == s)

}

case class Item(itemType: ItemType, price: BigDecimal)

trait ShoppingBasket {

   protected var items: List[Item] = List.empty

  def addToBasket(args: Array[String]): Unit = {
    args.foreach{
      item => item match {
        case "Soup" =>  add(Item(ItemType.Soup, 0.65))
        case "Bread" => add(Item(ItemType.Bread, 0.80))
        case "Milk"  => add(Item(ItemType.Milk, 1.30))
        case "Apple" => add(Item(ItemType.Apple, 1.00))
        case _ => throw new IllegalArgumentException(s"Unsupported Item type $item")
      }
    }
  }

  private def add(item: Item) = items = item :: items

  protected def subTotal() = items.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }

  def checkout(): BigDecimal = {
    println(s"Subtotal: £${"%.2f".format(subTotal())}")
    println("(No offers available)")
    subTotal
  }
}

trait DiscountOnPrice {
  self: ShoppingBasket =>

  override def checkout(): BigDecimal = {
    println(s"Subtotal: £${"%.2f".format(subTotal())}")
    items.groupBy(_.itemType).map { case (key, groupedItems) => key match {
      //Apple
      case ItemType.Apple => calculateAppleItems(groupedItems)
      //Soup
      case ItemType.Soup => groupedItems.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }
      //Bread
      case ItemType.Bread => calculateBreadItems(groupedItems, items.filter(item => item.itemType == ItemType.Soup))
      //Milk
      case ItemType.Milk => groupedItems.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }
    }
    }.foldLeft(BigDecimal(0)) { (acc, item) => acc + item }
  }

   private def  calculateAppleItems(groupedItems: List[Item]) = {
    val total = groupedItems.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }
      println("Apples 10% off: 10p")
      total - (total * .10)
  }

  private def calculateBreadItems(breadItem: List[Item], soups: List[Item]) = {
    println(s"(total bread items ${breadItem.length}, applying half price on ${soups.length / 2} bread items)")
    val numberOfSoups: Int = if(soups.nonEmpty) soups.length / 2 else 0
    breadItem.foldLeft((numberOfSoups, BigDecimal(0))) { (acc, item) => if (acc._1 != 0) (acc._1 - 1, acc._2 + (item.price / 2)) else (0, acc._2 + item.price)
    }._2
  }
}

