// Copyright David Abrahams 2005. Distributed under the Boost
// Software License, Version 1.0. (See accompanying
// file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
#ifndef BOOST_DETAIL_IS_XXX_DWA20051011_HPP
# define BOOST_DETAIL_IS_XXX_DWA20051011_HPP

# include <boost/config.hpp>
<<<<<<< HEAD
# include <boost/mpl/bool.hpp>
=======
# include <boost/type_traits/integral_constant.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
# include <boost/preprocessor/enum_params.hpp>


#  define BOOST_DETAIL_IS_XXX_DEF(name, qualified_name, nargs)  \
template <class T>                                              \
<<<<<<< HEAD
struct is_##name : mpl::false_                                  \
=======
struct is_##name : boost::false_type                            \
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{                                                               \
};                                                              \
                                                                \
template < BOOST_PP_ENUM_PARAMS_Z(1, nargs, class T) >          \
struct is_##name<                                               \
   qualified_name< BOOST_PP_ENUM_PARAMS_Z(1, nargs, T) >        \
>                                                               \
<<<<<<< HEAD
   : mpl::true_                                                 \
=======
   : boost::true_type                                           \
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{                                                               \
};


#endif // BOOST_DETAIL_IS_XXX_DWA20051011_HPP
