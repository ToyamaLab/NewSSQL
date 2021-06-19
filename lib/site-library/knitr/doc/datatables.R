<<<<<<< HEAD
## ----cool, results='asis'------------------------------------------------
library(knitr)
kable(mtcars, 'html', table.attr='id="mtcars_table"')

## ------------------------------------------------------------------------
options(markdown.HTML.header = system.file('misc', 'datatables.html', package = 'knitr'))

## ----boring, results='asis'----------------------------------------------
=======
## ----cool, results='asis'-----------------------------------------------------
library(knitr)
kable(mtcars, 'html', table.attr='id="mtcars_table"')

## -----------------------------------------------------------------------------
options(markdown.HTML.header = system.file('misc', 'datatables.html', package = 'knitr'))

## ----boring, results='asis'---------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
kable(head(mtcars), 'html')

