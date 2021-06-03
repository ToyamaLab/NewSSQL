/*=============================================================================
    Copyright (c) 2001-2011 Joel de Guzman
<<<<<<< HEAD

    Distributed under the Boost Software License, Version 1.0. (See accompanying 
=======
    Copyright (c) 2018 Kohei Takahashi

    Distributed under the Boost Software License, Version 1.0. (See accompanying
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
==============================================================================*/
#if !defined(FUSION_REMOVE_IF_07162005_0818)
#define FUSION_REMOVE_IF_07162005_0818

#include <boost/fusion/support/config.hpp>
#include <boost/fusion/view/filter_view/filter_view.hpp>
#include <boost/mpl/not.hpp>
<<<<<<< HEAD
#include <boost/type_traits/is_same.hpp>
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace fusion
{
    namespace result_of
    {
        template <typename Sequence, typename Pred>
        struct remove_if
        {
            typedef filter_view<Sequence, mpl::not_<Pred> > type;
        };
    }

    template <typename Pred, typename Sequence>
    BOOST_CONSTEXPR BOOST_FUSION_GPU_ENABLED
    inline typename result_of::remove_if<Sequence const, Pred>::type
    remove_if(Sequence const& seq)
    {
        typedef typename result_of::remove_if<Sequence const, Pred>::type result_type;
        return result_type(seq);
    }
}}

#endif

