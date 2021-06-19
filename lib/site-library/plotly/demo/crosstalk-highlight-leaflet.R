library(plotly)
# devtools::install_github("rstudio/leaflet#346")
library(leaflet)
library(crosstalk)
library(htmltools)

# leaflet should respect these "global" highlight() options
options(opacityDim = 0.5)

<<<<<<< HEAD
sd <- crosstalk_unit(quakes)
=======
sd <- highlight_key(quakes)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

p <- plot_ly(sd, x = ~depth, y = ~mag) %>% 
  add_markers(alpha = 0.5) %>%
  highlight("plotly_selected", dynamic = TRUE)

map <- leaflet(sd) %>% 
  addTiles() %>% 
  addCircles()

bscols(p, map)
