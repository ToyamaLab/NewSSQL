<<<<<<< HEAD
## ----setup, include=FALSE------------------------------------------------
library(knitr)

## ----eval=FALSE----------------------------------------------------------
=======
## ----setup, include=FALSE-----------------------------------------------------
library(knitr)

## ----eval=FALSE---------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  help(package = 'YourPackage', help_type = 'html')
#  # or see a standalone list of vignettes
#  browseVignettes('YourPackage')

<<<<<<< HEAD
## ----hello, results='asis'-----------------------------------------------
if (TRUE) cat('_hello_ **markdown**!', '\n')

## ----test, collapse=TRUE-------------------------------------------------
=======
## ----hello, results='asis'----------------------------------------------------
if (TRUE) cat('_hello_ **markdown**!', '\n')

## ----test, collapse=TRUE------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
1+1
10:1
rnorm(5)^2
strsplit('hello, markdown vignettes', '')

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
n=300; set.seed(123)
par(mar=c(4,4,.1,.1))
plot(rnorm(n), rnorm(n), pch=21, cex=5*runif(n), col='white', bg='gray')

<<<<<<< HEAD
## ----css, eval=FALSE-----------------------------------------------------
=======
## ----css, eval=FALSE----------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  options(markdown.HTML.stylesheet = 'path/to/a/custom/style.css')

