// Boost.Geometry

<<<<<<< HEAD
// Copyright (c) 2015, Oracle and/or its affiliates.
=======
// Copyright (c) 2015-2019, Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Distributed under the Boost Software License, Version 1.0.
// (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_EXPAND_EXPAND_BY_EPSILON_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_EXPAND_EXPAND_BY_EPSILON_HPP

#include <cstddef>
#include <algorithm>

<<<<<<< HEAD
#include <boost/type_traits/is_floating_point.hpp>
=======
#include <boost/type_traits/is_integral.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#include <boost/geometry/core/access.hpp>
#include <boost/geometry/core/coordinate_dimension.hpp>
#include <boost/geometry/core/coordinate_type.hpp>

#include <boost/geometry/util/math.hpp>

#include <boost/geometry/views/detail/indexed_point_view.hpp>

namespace boost { namespace geometry
{

#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace expand
{

template
<
    typename Point,
    template <typename> class PlusOrMinus,
    std::size_t I = 0,
<<<<<<< HEAD
    std::size_t D = dimension<Point>::value,
    bool Enable = boost::is_floating_point
                    <
                        typename coordinate_type<Point>::type
                    >::value
=======
    std::size_t D = dimension<Point>::value
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
>
struct corner_by_epsilon
{
    static inline void apply(Point & point)
    {
        typedef typename coordinate_type<Point>::type coord_type;
        coord_type const coord = get<I>(point);
<<<<<<< HEAD
        coord_type const eps = math::scaled_epsilon(coord);
        
        set<I>(point, PlusOrMinus<coord_type>()(coord, eps));

        corner_by_epsilon<Point, PlusOrMinus, I+1>::apply(point);
    }
=======
        coord_type const seps = math::scaled_epsilon(coord);
        
        set<I>(point, PlusOrMinus<coord_type>()(coord, seps));

        corner_by_epsilon<Point, PlusOrMinus, I+1>::apply(point);
    }

    static inline void apply(Point & point,
                             typename coordinate_type<Point>::type const& eps)
    {
        typedef typename coordinate_type<Point>::type coord_type;
        coord_type const coord = get<I>(point);
        coord_type const seps = math::scaled_epsilon(coord, eps);

        set<I>(point, PlusOrMinus<coord_type>()(coord, seps));

        corner_by_epsilon<Point, PlusOrMinus, I + 1>::apply(point);
    }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template
<
    typename Point,
    template <typename> class PlusOrMinus,
<<<<<<< HEAD
    std::size_t I,
    std::size_t D
>
struct corner_by_epsilon<Point, PlusOrMinus, I, D, false>
{
    static inline void apply(Point const&) {}
=======
    std::size_t D
>
struct corner_by_epsilon<Point, PlusOrMinus, D, D>
{
    static inline void apply(Point const&) {}
    static inline void apply(Point const&, typename coordinate_type<Point>::type const&) {}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template
<
<<<<<<< HEAD
    typename Point,
    template <typename> class PlusOrMinus,
    std::size_t D,
    bool Enable
>
struct corner_by_epsilon<Point, PlusOrMinus, D, D, Enable>
{
    static inline void apply(Point const&) {}
};

template
<
    typename Point,
    template <typename> class PlusOrMinus,
    std::size_t D
>
struct corner_by_epsilon<Point, PlusOrMinus, D, D, false>
{
    static inline void apply(Point const&) {}
=======
    typename Box,
    bool Enable = ! boost::is_integral<typename coordinate_type<Box>::type>::value
>
struct expand_by_epsilon
{
    static inline void apply(Box & box)
    {
        typedef detail::indexed_point_view<Box, min_corner> min_type;
        min_type min_point(box);
        corner_by_epsilon<min_type, std::minus>::apply(min_point);

        typedef detail::indexed_point_view<Box, max_corner> max_type;
        max_type max_point(box);
        corner_by_epsilon<max_type, std::plus>::apply(max_point);
    }

    static inline void apply(Box & box,
                             typename coordinate_type<Box>::type const& eps)
    {
        typedef detail::indexed_point_view<Box, min_corner> min_type;
        min_type min_point(box);
        corner_by_epsilon<min_type, std::minus>::apply(min_point, eps);

        typedef detail::indexed_point_view<Box, max_corner> max_type;
        max_type max_point(box);
        corner_by_epsilon<max_type, std::plus>::apply(max_point, eps);
    }
};

template <typename Box>
struct expand_by_epsilon<Box, false>
{
    static inline void apply(Box &) {}
    static inline void apply(Box &, typename coordinate_type<Box>::type const&) {}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

} // namespace expand

template <typename Box>
inline void expand_by_epsilon(Box & box)
{
<<<<<<< HEAD
    typedef detail::indexed_point_view<Box, min_corner> min_type;
    min_type min_point(box);
    expand::corner_by_epsilon<min_type, std::minus>::apply(min_point);

    typedef detail::indexed_point_view<Box, max_corner> max_type;
    max_type max_point(box);
    expand::corner_by_epsilon<max_type, std::plus>::apply(max_point);
=======
    expand::expand_by_epsilon<Box>::apply(box);
}

template <typename Box>
inline void expand_by_epsilon(Box & box,
                              typename coordinate_type<Box>::type const& eps)
{
    expand::expand_by_epsilon<Box>::apply(box, eps);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

} // namespace detail
#endif // DOXYGEN_NO_DETAIL

}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_EXPAND_EXPAND_BY_EPSILON_HPP
