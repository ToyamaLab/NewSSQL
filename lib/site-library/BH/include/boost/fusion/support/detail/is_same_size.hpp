/*=============================================================================
    Copyright (c) 2014-2015 Kohei Takahashi

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
==============================================================================*/
#ifndef FUSION_IS_SAME_SIZE_10082015_1156
#define FUSION_IS_SAME_SIZE_10082015_1156

#include <boost/fusion/support/is_sequence.hpp>
#include <boost/fusion/sequence/intrinsic/size.hpp>
<<<<<<< HEAD
#include <boost/core/enable_if.hpp>
#include <boost/mpl/bool.hpp>
#include <boost/mpl/equal_to.hpp>

namespace boost { namespace fusion { namespace detail
{
    template <typename Sequence1, typename Sequence2, typename = void, typename = void>
    struct is_same_size : mpl::false_ {};

    template <typename Sequence1, typename Sequence2>
    struct is_same_size<Sequence1, Sequence2,
                        typename enable_if<traits::is_sequence<Sequence1> >::type,
                        typename enable_if<traits::is_sequence<Sequence2> >::type>
        : mpl::equal_to<result_of::size<Sequence1>, result_of::size<Sequence2> >
=======
#include <boost/mpl/bool.hpp>

namespace boost { namespace fusion { namespace detail
{
    template <typename Sequence1, typename Sequence2, bool
            = traits::is_sequence<Sequence1>::value &&
              traits::is_sequence<Sequence2>::value>
    struct is_same_size : mpl::false_ {};

    template <typename Sequence1, typename Sequence2>
    struct is_same_size<Sequence1, Sequence2, true>
        : mpl::bool_<result_of::size<Sequence1>::value == 
                     result_of::size<Sequence2>::value>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {};
}}}

#endif
