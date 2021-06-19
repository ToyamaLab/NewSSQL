<<<<<<< HEAD
## ----setup, include=FALSE------------------------------------------------
=======
## ----setup, include=FALSE-----------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
library(knitr)
# to base64 encode images
opts_knit$set(upload.fun = image_uri)

<<<<<<< HEAD
## ----cars-demo-----------------------------------------------------------
=======
## ----cars-demo----------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
summary(cars)
fit=lm(dist~speed, data=cars)
summary(fit)

<<<<<<< HEAD
## ----cars-plot, fig.width=7, fig.height=6, fig.align='center'------------
par(mar=c(4,4,.1,.1))
plot(cars, pch=19)

## ----setup, eval=FALSE---------------------------------------------------
=======
## ----cars-plot, fig.width=7, fig.height=6, fig.align='center'-----------------
par(mar=c(4,4,.1,.1))
plot(cars, pch=19)

## ----setup, eval=FALSE--------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  library(knitr)
#  # to base64 encode images
#  opts_knit$set(upload.fun = image_uri)

