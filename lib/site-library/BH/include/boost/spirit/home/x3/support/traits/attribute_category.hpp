/*=============================================================================
    Copyright (c) 2001-2014 Joel de Guzman
    http://spirit.sourceforge.net/

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
=============================================================================*/
#if !defined(BOOST_SPIRIT_X3_ATTRIBUTE_CATEGORY_JAN_4_2012_1150AM)
#define BOOST_SPIRIT_X3_ATTRIBUTE_CATEGORY_JAN_4_2012_1150AM

#include <boost/mpl/identity.hpp>
#include <boost/mpl/logical.hpp>
#include <boost/mpl/eval_if.hpp>
<<<<<<< HEAD
#include <boost/fusion/include/copy.hpp>
#include <boost/fusion/include/is_sequence.hpp>
#include <boost/fusion/support/category_of.hpp>
#include <boost/spirit/home/x3/support/traits/is_variant.hpp>
#include <boost/spirit/home/x3/support/traits/container_traits.hpp>
=======
#include <boost/fusion/include/is_sequence.hpp>
#include <boost/fusion/support/category_of.hpp>
#include <boost/spirit/home/x3/support/traits/is_variant.hpp>
#include <boost/spirit/home/x3/support/traits/is_range.hpp>
#include <boost/spirit/home/x3/support/traits/container_traits.hpp>
#include <boost/spirit/home/x3/support/traits/optional_traits.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace spirit { namespace x3
{
   struct unused_type;
}}}

namespace boost { namespace spirit { namespace x3 { namespace traits
{
    struct unused_attribute {};
    struct plain_attribute {};
    struct container_attribute {};
    struct tuple_attribute {};
    struct associative_attribute {};
    struct variant_attribute {};
    struct optional_attribute {};
<<<<<<< HEAD
=======
    struct range_attribute {};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    template <typename T, typename Enable = void>
    struct attribute_category
        : mpl::identity<plain_attribute> {};

    template <>
    struct attribute_category<unused_type>
        : mpl::identity<unused_attribute> {};

    template <>
    struct attribute_category<unused_type const>
        : mpl::identity<unused_attribute> {};

    template <typename T>
    struct attribute_category< T
<<<<<<< HEAD
	, typename enable_if<
	      typename mpl::eval_if< 
		  fusion::traits::is_sequence<T>
		  , fusion::traits::is_associative<T>
		  , mpl::false_
		  >::type >::type >
=======
    , typename enable_if<
          typename mpl::eval_if<
          fusion::traits::is_sequence<T>
          , fusion::traits::is_associative<T>
          , mpl::false_
          >::type >::type >
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        : mpl::identity<associative_attribute> {};

    template <typename T>
    struct attribute_category< T
<<<<<<< HEAD
	, typename enable_if<
	      mpl::and_<
		  fusion::traits::is_sequence<T>
		  , mpl::not_<fusion::traits::is_associative<T> > 
		  > >::type >
=======
    , typename enable_if<
          mpl::and_<
          fusion::traits::is_sequence<T>
          , mpl::not_<fusion::traits::is_associative<T> >
          > >::type >
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        : mpl::identity<tuple_attribute> {};

    template <typename T>
    struct attribute_category<T,
        typename enable_if<traits::is_variant<T>>::type>
        : mpl::identity<variant_attribute> {};

    template <typename T>
    struct attribute_category<T,
<<<<<<< HEAD
        typename enable_if<traits::is_container<T>>::type>
=======
        typename enable_if<traits::is_optional<T>>::type>
        : mpl::identity<optional_attribute> {};

    template <typename T>
    struct attribute_category<T,
        typename enable_if<traits::is_range<T>>::type>
        : mpl::identity<range_attribute> {};

    template <typename T>
    struct attribute_category< T
    , typename enable_if<
          mpl::and_<
          traits::is_container<T>
          , mpl::not_<fusion::traits::is_sequence<T> >
          , mpl::not_<traits::is_range<T> >
          > >::type >
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        : mpl::identity<container_attribute> {};

}}}}

#endif
