// Boost.Geometry (aka GGL, Generic Geometry Library)

// Copyright (c) 2007-2012 Barend Gehrels, Amsterdam, the Netherlands.
// Copyright (c) 2008-2012 Bruno Lalande, Paris, France.
// Copyright (c) 2009-2012 Mateusz Loskot, London, UK.

<<<<<<< HEAD
=======
// This file was modified by Oracle on 2018, 2019.
// Modifications copyright (c) 2018, 2019 Oracle and/or its affiliates.
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Parts of Boost.Geometry are redesigned from Geodan's Geographic Library
// (geolib/GGL), copyright (c) 1995-2010 Geodan, Amsterdam, the Netherlands.

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_STRATEGIES_CONCEPTS_WITHIN_CONCEPT_HPP
#define BOOST_GEOMETRY_STRATEGIES_CONCEPTS_WITHIN_CONCEPT_HPP



#include <boost/concept_check.hpp>
<<<<<<< HEAD
#include <boost/function_types/result_type.hpp>

=======
#include <boost/core/ignore_unused.hpp>
#include <boost/function_types/result_type.hpp>

#include <boost/geometry/core/tag.hpp>
#include <boost/geometry/core/tag_cast.hpp>
#include <boost/geometry/core/tags.hpp>

#include <boost/geometry/geometries/concepts/box_concept.hpp>
#include <boost/geometry/geometries/concepts/point_concept.hpp>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/geometry/util/parameter_type_of.hpp>


namespace boost { namespace geometry { namespace concepts
{


/*!
\brief Checks strategy for within (point-in-polygon)
\ingroup within
*/
<<<<<<< HEAD
template <typename Strategy>
=======
template <typename Point, typename Polygonal, typename Strategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
class WithinStrategyPolygonal
{
#ifndef DOXYGEN_NO_CONCEPT_MEMBERS

<<<<<<< HEAD
=======
    typedef typename geometry::point_type<Polygonal>::type point_of_segment;

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    // 1) must define state_type
    typedef typename Strategy::state_type state_type;

    struct checker
    {
        template <typename ApplyMethod, typename ResultMethod>
<<<<<<< HEAD
        static void apply(ApplyMethod const&, ResultMethod const& )
=======
        static void apply(ApplyMethod, ResultMethod)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            typedef typename parameter_type_of
                <
                    ApplyMethod, 0
                >::type point_type;
            typedef typename parameter_type_of
                <
                    ApplyMethod, 1
                >::type segment_point_type;

            // CHECK: apply-arguments should both fulfill point concept
            BOOST_CONCEPT_ASSERT
                (
                    (concepts::ConstPoint<point_type>)
                );

            BOOST_CONCEPT_ASSERT
                (
                    (concepts::ConstPoint<segment_point_type>)
                );

            // CHECK: return types (result: int, apply: bool)
            BOOST_MPL_ASSERT_MSG
                (
                    (boost::is_same
                        <
                            bool, typename boost::function_types::result_type<ApplyMethod>::type
                        >::type::value),
                    WRONG_RETURN_TYPE_OF_APPLY
                    , (bool)
                );
            BOOST_MPL_ASSERT_MSG
                (
                    (boost::is_same
                        <
                            int, typename boost::function_types::result_type<ResultMethod>::type
                        >::type::value),
                    WRONG_RETURN_TYPE_OF_RESULT
                    , (int)
                );


            // CHECK: calling method apply and result
            Strategy const* str = 0;
            state_type* st = 0;
            point_type const* p = 0;
            segment_point_type const* sp = 0;

            bool b = str->apply(*p, *sp, *sp, *st);
            int r = str->result(*st);

<<<<<<< HEAD
            boost::ignore_unused_variable_warning(r);
            boost::ignore_unused_variable_warning(b);
            boost::ignore_unused_variable_warning(str);
=======
            boost::ignore_unused(r, b, str);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
    };


public :
    BOOST_CONCEPT_USAGE(WithinStrategyPolygonal)
    {
<<<<<<< HEAD
        checker::apply(&Strategy::apply, &Strategy::result);
=======
        checker::apply(&Strategy::template apply<Point, point_of_segment>,
                       &Strategy::result);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
#endif
};

<<<<<<< HEAD
template <typename Strategy>
=======
template <typename Point, typename Box, typename Strategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
class WithinStrategyPointBox
{
#ifndef DOXYGEN_NO_CONCEPT_MEMBERS

    struct checker
    {
        template <typename ApplyMethod>
<<<<<<< HEAD
        static void apply(ApplyMethod const&)
=======
        static void apply(ApplyMethod)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            typedef typename parameter_type_of
                <
                    ApplyMethod, 0
                >::type point_type;
            typedef typename parameter_type_of
                <
                    ApplyMethod, 1
                >::type box_type;

            // CHECK: apply-arguments should fulfill point/box concept
            BOOST_CONCEPT_ASSERT
                (
                    (concepts::ConstPoint<point_type>)
                );

            BOOST_CONCEPT_ASSERT
                (
                    (concepts::ConstBox<box_type>)
                );

            // CHECK: return types (apply: bool)
            BOOST_MPL_ASSERT_MSG
                (
                    (boost::is_same
                        <
                            bool,
                            typename boost::function_types::result_type<ApplyMethod>::type
                        >::type::value),
                    WRONG_RETURN_TYPE
                    , (bool)
                );


            // CHECK: calling method apply
            Strategy const* str = 0;
            point_type const* p = 0;
            box_type const* bx = 0;

            bool b = str->apply(*p, *bx);

<<<<<<< HEAD
            boost::ignore_unused_variable_warning(b);
            boost::ignore_unused_variable_warning(str);
=======
            boost::ignore_unused(b, str);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
    };


public :
    BOOST_CONCEPT_USAGE(WithinStrategyPointBox)
    {
<<<<<<< HEAD
        checker::apply(&Strategy::apply);
=======
        checker::apply(&Strategy::template apply<Point, Box>);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
#endif
};

<<<<<<< HEAD
template <typename Strategy>
=======
template <typename Box1, typename Box2, typename Strategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
class WithinStrategyBoxBox
{
#ifndef DOXYGEN_NO_CONCEPT_MEMBERS

    struct checker
    {
        template <typename ApplyMethod>
        static void apply(ApplyMethod const&)
        {
            typedef typename parameter_type_of
                <
                    ApplyMethod, 0
                >::type box_type1;
            typedef typename parameter_type_of
                <
                    ApplyMethod, 1
                >::type box_type2;

            // CHECK: apply-arguments should both fulfill box concept
            BOOST_CONCEPT_ASSERT
                (
                    (concepts::ConstBox<box_type1>)
                );

            BOOST_CONCEPT_ASSERT
                (
                    (concepts::ConstBox<box_type2>)
                );

            // CHECK: return types (apply: bool)
            BOOST_MPL_ASSERT_MSG
                (
                    (boost::is_same
                        <
                            bool,
                            typename boost::function_types::result_type<ApplyMethod>::type
                        >::type::value),
                    WRONG_RETURN_TYPE
                    , (bool)
                );


            // CHECK: calling method apply
            Strategy const* str = 0;
            box_type1 const* b1 = 0;
            box_type2 const* b2 = 0;

            bool b = str->apply(*b1, *b2);

<<<<<<< HEAD
            boost::ignore_unused_variable_warning(b);
            boost::ignore_unused_variable_warning(str);
=======
            boost::ignore_unused(b, str);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
    };


public :
    BOOST_CONCEPT_USAGE(WithinStrategyBoxBox)
    {
<<<<<<< HEAD
        checker::apply(&Strategy::apply);
=======
        checker::apply(&Strategy::template apply<Box1, Box2>);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
#endif
};

// So now: boost::geometry::concepts::within
namespace within
{

#ifndef DOXYGEN_NO_DISPATCH
namespace dispatch
{

<<<<<<< HEAD
template <typename FirstTag, typename SecondTag, typename CastedTag, typename Strategy>
=======
template
<
    typename Geometry1, typename Geometry2,
    typename FirstTag, typename SecondTag, typename CastedTag,
    typename Strategy
>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct check_within
{};


<<<<<<< HEAD
template <typename AnyTag, typename Strategy>
struct check_within<point_tag, AnyTag, areal_tag, Strategy>
{
    BOOST_CONCEPT_ASSERT( (WithinStrategyPolygonal<Strategy>) );
};


template <typename Strategy>
struct check_within<point_tag, box_tag, areal_tag, Strategy>
{
    BOOST_CONCEPT_ASSERT( (WithinStrategyPointBox<Strategy>) );
};

template <typename Strategy>
struct check_within<box_tag, box_tag, areal_tag, Strategy>
{
    BOOST_CONCEPT_ASSERT( (WithinStrategyBoxBox<Strategy>) );
=======
template
<
    typename Geometry1, typename Geometry2,
    typename AnyTag,
    typename Strategy
>
struct check_within<Geometry1, Geometry2, point_tag, AnyTag, areal_tag, Strategy>
{
    BOOST_CONCEPT_ASSERT( (WithinStrategyPolygonal<Geometry1, Geometry2, Strategy>) );
};


template <typename Geometry1, typename Geometry2, typename Strategy>
struct check_within<Geometry1, Geometry2, point_tag, box_tag, areal_tag, Strategy>
{
    BOOST_CONCEPT_ASSERT( (WithinStrategyPointBox<Geometry1, Geometry2, Strategy>) );
};

template <typename Geometry1, typename Geometry2, typename Strategy>
struct check_within<Geometry1, Geometry2, box_tag, box_tag, areal_tag, Strategy>
{
    BOOST_CONCEPT_ASSERT( (WithinStrategyBoxBox<Geometry1, Geometry2, Strategy>) );
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};


} // namespace dispatch
#endif


/*!
\brief Checks, in compile-time, the concept of any within-strategy
\ingroup concepts
*/
<<<<<<< HEAD
template <typename FirstTag, typename SecondTag, typename CastedTag, typename Strategy>
inline void check()
{
    dispatch::check_within<FirstTag, SecondTag, CastedTag, Strategy> c;
    boost::ignore_unused_variable_warning(c);
=======
template <typename Geometry1, typename Geometry2, typename Strategy>
inline void check()
{
    dispatch::check_within
        <
            Geometry1,
            Geometry2,
            typename tag<Geometry1>::type,
            typename tag<Geometry2>::type,
            typename tag_cast<typename tag<Geometry2>::type, areal_tag>::type,
            Strategy
        > c;
    boost::ignore_unused(c);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}


}}}} // namespace boost::geometry::concepts::within


#endif // BOOST_GEOMETRY_STRATEGIES_CONCEPTS_WITHIN_CONCEPT_HPP
