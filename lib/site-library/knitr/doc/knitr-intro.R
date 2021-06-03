<<<<<<< HEAD
## ----show-off, tidy=TRUE-------------------------------------------------
=======
## ----show-off, tidy=TRUE------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
options(digits=4)
rnorm(20)
fit=lm(dist~speed, data=cars)
b=coef(fit)

<<<<<<< HEAD
## ----results='asis', echo=FALSE------------------------------------------
knitr::kable(summary(fit)$coefficients, caption='Regression coefficients.')

## ----graphics, fig.cap='A scatterplot with a regression line.'-----------
=======
## ----results='asis', echo=FALSE-----------------------------------------------
knitr::kable(summary(fit)$coefficients, caption='Regression coefficients.')

## ----graphics, fig.cap='A scatterplot with a regression line.'----------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
par(mar=c(4, 4, 1, .1))
plot(cars, pch = 20)
abline(fit, col = 'red')

