<<<<<<< HEAD
## ----hello, results='asis'-----------------------------------------------
cat('_hello_ **markdown**!', '\n')

## ------------------------------------------------------------------------
=======
## ----hello, results='asis'----------------------------------------------------
cat('_hello_ **markdown**!', '\n')

## -----------------------------------------------------------------------------
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
## ------------------------------------------------------------------------
head(knitr::rocco, 5)

## ----setup, echo=FALSE, results='asis'-----------------------------------
=======
## -----------------------------------------------------------------------------
head(knitr::rocco, 5)

## ----setup, echo=FALSE, results='asis'----------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
x = readLines('docco-linear.Rmd')[-(1:7)]
x = gsub('linear', 'classic', x)
i = grep('^knit2html[(][.]{3}', x)
x[i - 1] = '```{r}'
x[i] = 'head(knitr::rocco, 5)'
library(knitr)
cat(knit_child(text = x, quiet = TRUE), sep = '\n')

