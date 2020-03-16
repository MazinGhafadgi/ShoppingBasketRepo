package uk.adthena.shoppingbasket

object ShoppingBasketAppWithDiscount {

  def main(args: Array[String]): Unit = {
    object ShoppingAppWithDiscount extends ShoppingBasket with DiscountOnPrice
    import ShoppingAppWithDiscount._

    args.foreach{
      item => item match {
        case "Soup" =>  addToBasket(Item(ItemType.Soup, 0.65))
        case "Bread" => addToBasket(Item(ItemType.Bread, 0.80))
        case "Milk"  => addToBasket(Item(ItemType.Milk, 1.30))
        case "Apple" => addToBasket(Item(ItemType.Apple, 1.00))
        case _ => throw new IllegalArgumentException(s"Unsupported Item type $item")
      }
    }
    println(s"Total price: £${"%.2f".format(checkout())}")
  }

}
