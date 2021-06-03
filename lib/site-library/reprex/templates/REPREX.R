{{{yaml}}}

{{{so_syntax_highlighting}}}

#+ reprex-setup, include = FALSE
options(tidyverse.quiet = {{{tidyverse_quiet}}})
knitr::opts_chunk$set(collapse = TRUE, comment = "{{{comment}}}", error = TRUE)
knitr::opts_knit$set(upload.fun = {{{upload_fun}}})
<<<<<<< HEAD
{{{user_opts_chunk}}}
{{{user_opts_knit}}}
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#+ reprex-body
{{{body}}}

{{{std_file_stub}}}

<<<<<<< HEAD
{{#advertisement}}
#' Created on `r Sys.Date()` by the [reprex package](http://reprex.tidyverse.org) (v`r utils::packageVersion("reprex")`).
{{/advertisement}}

{{#si}}
{{{si_start}}}
{{{si}}}
{{{si_end}}}
{{/si}}
=======
{{{ad}}}

{{{si}}}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
