///////////////////////////////////////////////////////////////////////////////
//  Copyright 2015 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_IS_BYTE_CONTAINER_HPP
#define BOOST_IS_BYTE_CONTAINER_HPP

<<<<<<< HEAD
#include <boost/mpl/has_xxx.hpp>
#include <boost/type_traits/is_integral.hpp>

namespace boost{ namespace multiprecision{ namespace detail{

   BOOST_MPL_HAS_XXX_TRAIT_NAMED_DEF(has_member_value_type, value_type, false)
   BOOST_MPL_HAS_XXX_TRAIT_NAMED_DEF(has_member_const_iterator, const_iterator, false)

   template <class C, bool b>
   struct is_byte_container_imp
   {
      static const bool value = boost::is_integral<typename C::value_type>::value && (sizeof(typename C::value_type) == 1);
   };

   template <class C>
   struct is_byte_container_imp<C, false> : public boost::false_type {};

   template <class C>
   struct is_byte_container : public is_byte_container_imp<C, has_member_value_type<C>::value && has_member_const_iterator<C>::value> {};


}}} // namespaces

#endif // BOOST_IS_BYTE_CONTAINER_HPP

=======
#include <iterator>
#include <boost/mpl/has_xxx.hpp>
#include <boost/type_traits/is_integral.hpp>
#include <boost/type_traits/remove_cv.hpp>

namespace boost { namespace multiprecision { namespace detail {

BOOST_MPL_HAS_XXX_TRAIT_NAMED_DEF(has_member_const_iterator, const_iterator, false)

template <class C, bool b>
struct is_byte_container_imp
{
   // Note: Don't use C::value_type as this is a rather widespread typedef, even for non-range types
   typedef typename boost::remove_cv<typename std::iterator_traits<typename C::const_iterator>::value_type>::type container_value_type;
   static const bool                                                                                              value = boost::is_integral<container_value_type>::value && (sizeof(container_value_type) == 1);
};

template <class C>
struct is_byte_container_imp<C, false> : public boost::false_type
{};

template <class C>
struct is_byte_container : public is_byte_container_imp<C, has_member_const_iterator<C>::value>
{};

}}} // namespace boost::multiprecision::detail

#endif // BOOST_IS_BYTE_CONTAINER_HPP
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
