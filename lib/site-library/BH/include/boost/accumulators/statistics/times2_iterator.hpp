///////////////////////////////////////////////////////////////////////////////
// times2_iterator.hpp
//
//  Copyright 2006 Eric Niebler. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_ACCUMULATORS_STATISTICS_TIMES2_ITERATOR_HPP_DE_01_01_2006
#define BOOST_ACCUMULATORS_STATISTICS_TIMES2_ITERATOR_HPP_DE_01_01_2006

#include <functional>
<<<<<<< HEAD
=======
#include <boost/detail/workaround.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/range/begin.hpp>
#include <boost/range/end.hpp>
#include <boost/range/iterator_range.hpp>
#include <boost/iterator/transform_iterator.hpp>
#include <boost/iterator/counting_iterator.hpp>
#include <boost/iterator/permutation_iterator.hpp>

namespace boost { namespace accumulators
{

namespace detail
{
    typedef transform_iterator<
<<<<<<< HEAD
        std::binder1st<std::multiplies<std::size_t> >
=======
#ifdef BOOST_NO_CXX98_BINDERS
        decltype(std::bind(std::multiplies<std::size_t>(), 2, std::placeholders::_1))
#else
        std::binder1st<std::multiplies<std::size_t> >
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      , counting_iterator<std::size_t>
    > times2_iterator;

    inline times2_iterator make_times2_iterator(std::size_t i)
    {
        return make_transform_iterator(
            make_counting_iterator(i)
<<<<<<< HEAD
          , std::bind1st(std::multiplies<std::size_t>(), 2)
        );
    }


=======
#ifdef BOOST_NO_CXX98_BINDERS
          , std::bind(std::multiplies<std::size_t>(), 2, std::placeholders::_1)
#else
          , std::bind1st(std::multiplies<std::size_t>(), 2)
#endif
        );
    }

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    ///////////////////////////////////////////////////////////////////////////////
    // lvalue_index_iterator
    template<typename Base>
    struct lvalue_index_iterator
      : Base
    {
<<<<<<< HEAD
=======
        lvalue_index_iterator()
          : Base()
        {}

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        lvalue_index_iterator(Base base)
          : Base(base)
        {
        }

        typename Base::reference operator [](typename Base::difference_type n) const
        {
            return *(*this + n);
        }
    };
} // namespace detail

}}

#endif
