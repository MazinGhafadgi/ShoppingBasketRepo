package uk.adthena.shoppingbasket

import uk.adthena.shoppingbasket.ItemType.ItemType

object ItemType extends Enumeration {
  type ItemType = Value
  val Soup, Bread, Milk, Apple = Value
  def withNameOpt(s: String): Option[ItemType] = values.find(_.toString == s)

}

case class Item(itemType: ItemType, price: BigDecimal)

trait ShoppingBasket {
  private var items: List[Item] = List.empty

  def addToBasket(item: Item) = items = item :: items

  def calculateAppleItems(groupedItems: List[Item]) = groupedItems.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }
  def calculateBreadItems(groupedItems: List[Item], soups: List[Item]) = groupedItems.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }

  def checkout() = {
    println(s"Subtotal: £${"%.2f".format(items.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price })}")

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
}

trait DiscountOnPrice {
  self: ShoppingBasket =>
   override def  calculateAppleItems(groupedItems: List[Item]) = {
    val total = groupedItems.foldLeft(BigDecimal(0)) { (acc, item) => acc + item.price }
      println("Apples 10% off: 10p")
      total - (total * .10)
  }

  override def calculateBreadItems(breadItem: List[Item], soups: List[Item]) = {
    println(s"(total bread items ${breadItem.length}, applying half price on ${soups.length / 2} bread items)")
    val numberOfSoups: Int = if(soups.nonEmpty) soups.length / 2 else 0
    breadItem.foldLeft((numberOfSoups, BigDecimal(0))) { (acc, item) => if (acc._1 != 0) (acc._1 - 1, acc._2 + (item.price / 2)) else (0, acc._2 + item.price)
    }._2
  }
}

