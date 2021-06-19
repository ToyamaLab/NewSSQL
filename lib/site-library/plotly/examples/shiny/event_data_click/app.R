library(plotly)
library(shiny)

<<<<<<< HEAD
# compute a correlation matrix
correlation <- round(cor(mtcars), 3)
nms <- names(mtcars)
=======
# cache computation of a correlation matrix
correlation <- round(cor(mtcars), 3)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

ui <- fluidPage(
  mainPanel(
    plotlyOutput("heat"),
    plotlyOutput("scatterplot")
  ),
  verbatimTextOutput("selection")
)

server <- function(input, output, session) {
<<<<<<< HEAD
  output$heat <- renderPlotly({
    plot_ly(x = nms, y = nms, z = correlation, 
            key = correlation, type = "heatmap", source = "heatplot") %>%
      layout(xaxis = list(title = ""), 
             yaxis = list(title = ""))
  })
  
  output$selection <- renderPrint({
    s <- event_data("plotly_click", source = "heatplot")
=======
  
  output$heat <- renderPlotly({
    plot_ly(source = "heatmap") %>%
      add_heatmap(
        x = names(mtcars), 
        y = names(mtcars), 
        z = correlation
      ) %>%
      layout(
        xaxis = list(title = ""), 
        yaxis = list(title = "")
      )
  })
  
  output$selection <- renderPrint({
    s <- event_data("plotly_click", source = "heatmap")
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    if (length(s) == 0) {
      "Click on a cell in the heatmap to display a scatterplot"
    } else {
      cat("You selected: \n\n")
      as.list(s)
    }
  })
  
  output$scatterplot <- renderPlotly({
<<<<<<< HEAD
    s <- event_data("plotly_click", source = "heatplot")
    if (length(s)) {
      vars <- c(s[["x"]], s[["y"]])
      d <- setNames(mtcars[vars], c("x", "y"))
      yhat <- fitted(lm(y ~ x, data = d))
      plot_ly(d, x = ~x) %>%
        add_markers(y = ~y) %>%
        add_lines(y = ~yhat) %>%
        layout(xaxis = list(title = s[["x"]]), 
               yaxis = list(title = s[["y"]]), 
               showlegend = FALSE)
    } else {
      plotly_empty()
    }
=======
    clickData <- event_data("plotly_click", source = "heatmap")
    if (is.null(clickData)) return(NULL)
    
    # get the clicked x/y variables and fit model to those 2 vars
    vars <- c(clickData[["x"]], clickData[["y"]])
    d <- setNames(mtcars[vars], c("x", "y"))
    yhat <- fitted(lm(y ~ x, data = d))
    
    # scatterplot with fitted line
    plot_ly(d, x = ~x) %>%
      add_markers(y = ~y) %>%
      add_lines(y = ~yhat) %>%
      layout(
        xaxis = list(title = clickData[["x"]]), 
        yaxis = list(title = clickData[["y"]]), 
        showlegend = FALSE
      )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
  })
  
}

shinyApp(ui, server, options = list(display.mode = "showcase"))
