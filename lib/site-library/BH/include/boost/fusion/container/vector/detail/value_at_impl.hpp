/*=============================================================================
<<<<<<< HEAD
    Copyright (c) 2014 Kohei Takahashi
=======
    Copyright (c) 2014,2018 Kohei Takahashi
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
==============================================================================*/
#ifndef FUSION_VALUE_AT_IMPL_16122014_1641
#define FUSION_VALUE_AT_IMPL_16122014_1641

<<<<<<< HEAD
#include <boost/config.hpp>
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/fusion/support/config.hpp>
#include <boost/fusion/container/vector/detail/config.hpp>

///////////////////////////////////////////////////////////////////////////////
// Without variadics, we will use the PP version
///////////////////////////////////////////////////////////////////////////////
#if !defined(BOOST_FUSION_HAS_VARIADIC_VECTOR)
# include <boost/fusion/container/vector/detail/cpp03/value_at_impl.hpp>
#else

///////////////////////////////////////////////////////////////////////////////
// C++11 interface
///////////////////////////////////////////////////////////////////////////////
#include <boost/fusion/container/vector/vector_fwd.hpp>
#include <boost/type_traits/declval.hpp>
<<<<<<< HEAD
=======
#include <boost/mpl/identity.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace fusion
{
    struct vector_tag;

    namespace vector_detail
    {
        template <std::size_t I, typename T>
        struct store;

        template <std::size_t N, typename U>
        static inline BOOST_FUSION_GPU_ENABLED
<<<<<<< HEAD
        U value_at_impl(store<N, U> const volatile*);
=======
        mpl::identity<U> value_at_impl(store<N, U> const volatile*);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

    namespace extension
    {
        template <typename Tag>
        struct value_at_impl;

        template <>
        struct value_at_impl<vector_tag>
        {
            template <typename Sequence, typename N>
<<<<<<< HEAD
            struct apply
            {
                typedef
                    decltype(vector_detail::value_at_impl<N::value>(boost::declval<Sequence*>()))
                type;
            };
=======
            struct apply : BOOST_FUSION_DECLTYPE_N3031((
                    vector_detail::value_at_impl<N::value>(boost::declval<Sequence*>())
                ))
            {};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        };
    }
}}

#endif
#endif

