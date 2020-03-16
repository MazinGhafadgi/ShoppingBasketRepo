package uk.adthena.shoppingbasket

import org.scalatest.{FlatSpec, GivenWhenThen}

class ShoppingBasketSpec extends FlatSpec with GivenWhenThen {

  it should "calculate the total items and apply discount to apple item" in {
    object ShoppingApp extends ShoppingBasket with DiscountOnPrice
     import ShoppingApp._
    Given("The shopping basket has Apple, Milk and Bread")
      addToBasket(Item(ItemType.Apple, 1.00))
      addToBasket(Item(ItemType.Milk, 1.30))
      addToBasket(Item(ItemType.Bread, 0.80))
    When("checkout")
    val totalPrice = s"£${"%.2f".format(checkout())}"
    Then(" expected Total price should be £3.00")
    assert(totalPrice == "£3.00")
      println(s"Total price: £$totalPrice")
  }

  it should "calculate the total items and no discount applied" in {
    object ShoppingApp extends ShoppingBasket
    import ShoppingApp._
    Given("The shopping basket has Apple, Milk and Bread")
    addToBasket(Item(ItemType.Apple, 1.00))
    addToBasket(Item(ItemType.Milk, 1.30))
    addToBasket(Item(ItemType.Bread, 0.80))
    When("checkout")
    val totalPrice = s"£${"%.2f".format(checkout())}"
    Then(" expected Total price should be £3.10")
    assert(totalPrice == "£3.10")
    println(s"Total price: $totalPrice}")
  }

  it should "apply special offers on bread items " in {
    object ShoppingApp extends ShoppingBasket with DiscountOnPrice
    import ShoppingApp._
    Given("The shopping basket has six tin of Soup and four loaf of bread")
      addToBasket(Item(ItemType.Soup, 0.65))
      addToBasket(Item(ItemType.Soup, 0.65))
      addToBasket(Item(ItemType.Soup, 0.65))
      addToBasket(Item(ItemType.Soup, 0.65))
      addToBasket(Item(ItemType.Soup, 0.65))
      addToBasket(Item(ItemType.Soup, 0.65))
      addToBasket(Item(ItemType.Bread, 0.80))
      addToBasket(Item(ItemType.Bread, 0.80))
      addToBasket(Item(ItemType.Bread, 0.80))
      addToBasket(Item(ItemType.Bread, 0.80))
    When("checkout")
    val totalPrice = s"£${"%.2f".format(checkout())}"
    Then("Total price should £5.90")
      assert(totalPrice == "£5.90")
      println(s"Total price: $totalPrice")
  }

  it should "apply special offers on Apple item only" in {
    object ShoppingApp extends ShoppingBasket with DiscountOnPrice
    import ShoppingApp._
    Given("The shopping has four loaf of bread and one apple")
    addToBasket(Item(ItemType.Apple, 1.00))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    When("checkout")
    val totalPrice = s"£${"%.2f".format(checkout())}"
    Then("Total price should £4.10")
    assert(totalPrice == "£4.10")
    println(s"Total price: $totalPrice")
  }

  it should "apply special offers on Apple/Bread items " in {
    object ShoppingApp extends ShoppingBasket with DiscountOnPrice
    import ShoppingApp._
    Given("The shopping has two loaf of bread, two tin of soup and one apple")
    addToBasket(Item(ItemType.Apple, 1.00))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    When("checkout")
    val totalPrice = s"£${"%.2f".format(checkout())}"
    Then("Total price should £3.40")
    assert(totalPrice == "£3.40")
    println(s"Total price: $totalPrice")
  }

  it should "Not apply special offers on bread items when discount not applied" in {
    object ShoppingApp extends ShoppingBasket
    import ShoppingApp._
    Given("The shopping basket has six tin of Soup and four loaf of bread")
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Soup, 0.65))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    addToBasket(Item(ItemType.Bread, 0.80))
    When("checkout")
    val totalPrice = s"£${"%.2f".format(checkout())}"
    Then("Total price should £7.10")
    assert(totalPrice == "£7.10")
    println(s"Total price: $totalPrice")
  }

}
