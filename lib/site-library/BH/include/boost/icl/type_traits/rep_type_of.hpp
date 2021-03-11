/*-----------------------------------------------------------------------------+
Copyright (c) 2008-2009: Joachim Faulhaber
+------------------------------------------------------------------------------+
   Distributed under the Boost Software License, Version 1.0.
      (See accompanying file LICENCE.txt or copy at
           http://www.boost.org/LICENSE_1_0.txt)
+-----------------------------------------------------------------------------*/
#ifndef BOOST_ICL_TYPE_TRAITS_REP_TYPE_OF_HPP_JOFA_110329
#define BOOST_ICL_TYPE_TRAITS_REP_TYPE_OF_HPP_JOFA_110329

#include <boost/config.hpp> // For macro BOOST_STATIC_CONSTANT
#include <boost/mpl/has_xxx.hpp>
#include <boost/mpl/or.hpp>
#include <boost/mpl/and.hpp>
#include <boost/mpl/not.hpp>
<<<<<<< HEAD
=======
#include <boost/type_traits/is_same.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/icl/type_traits/no_type.hpp>

namespace boost{ namespace icl
{
    namespace detail
    {
        BOOST_MPL_HAS_XXX_TRAIT_DEF(rep)
    }

    //--------------------------------------------------------------------------
    template <class Type>
<<<<<<< HEAD
    struct has_rep_type 
=======
    struct has_rep_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      : mpl::bool_<detail::has_rep<Type>::value>
    {};

    template <class Rep, class Type>
    struct represents // Rep represents Type;  Type is_wrapper_of Rep
      : mpl::bool_<detail::has_rep<Type>::value>
    {
        typedef represents type;
<<<<<<< HEAD
        BOOST_STATIC_CONSTANT(bool, 
            value = (mpl::and_< has_rep_type<Type>
                              , is_same<typename Type::rep, Rep> >::value)
=======
        BOOST_STATIC_CONSTANT(bool,
            value = (mpl::and_< has_rep_type<Type>
                              , boost::is_same<typename Type::rep, Rep> >::value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            );
    };

    //--------------------------------------------------------------------------
<<<<<<< HEAD
    template <class Type, bool has_rep> 
=======
    template <class Type, bool has_rep>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    struct get_rep_type;

    template <class Type>
    struct get_rep_type<Type, false>
    {
        typedef no_type type;
    };

    template <class Type>
    struct get_rep_type<Type, true>
    {
        typedef typename Type::rep type;
    };

    //--------------------------------------------------------------------------
<<<<<<< HEAD
    template<class Type> 
    struct rep_type_of
    { 
        typedef typename 
=======
    template<class Type>
    struct rep_type_of
    {
        typedef typename
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            get_rep_type<Type, has_rep_type<Type>::value>::type type;
    };

}} // namespace boost icl

#endif
<<<<<<< HEAD


=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
