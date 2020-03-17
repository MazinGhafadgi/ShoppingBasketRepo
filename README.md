# ShoppingBasketRepo

git clone https://github.com/MazinGhafadgi/ShoppingBasketRepo.git

# run the test
   testOnly uk.adthena.shoppingbasket.ShoppingBasketSpec


# run the application with 'ShoppingBasketApp'
uk.adthena.shoppingbasket.ShoppingBasketApp
with command line arguments
example Apple Soup Milk


# run the application with discount application 'ShoppingBasketAppWithDiscount'
uk.adthena.shoppingbasket.ShoppingBasketAppWithDiscount
with command line arguments
example Soup Soup Milk Bread Soup Apple


# How to the application from sbt command line
1. in your terminal type sbt
2. run Apple Apple Milk

The sbt will promote you with 'Multiple main classes detected, select one to run:'
 [1] uk.adthena.shoppingbasket.ShoppingBasketApp
 [2] uk.adthena.shoppingbasket.ShoppingBasketAppWithDiscount

Enter number: 1 or 2


#See example below
type sbt
sbt:shopping cart> run Apple Apple
[warn] Multiple main classes detected.  Run 'show discoveredMainClasses' to see the list

Multiple main classes detected, select one to run:

 [1] uk.adthena.shoppingbasket.ShoppingBasketApp
 [2] uk.adthena.shoppingbasket.ShoppingBasketAppWithDiscount

Enter number: 2

[info] Running uk.adthena.shoppingbasket.ShoppingBasketAppWithDiscount Apple Apple
Subtotal: £2.00
Apples 10% off: 10p
Total price: £1.80
[success] Total time: 3 s, completed 17-Mar-2020 09:23:53
s


